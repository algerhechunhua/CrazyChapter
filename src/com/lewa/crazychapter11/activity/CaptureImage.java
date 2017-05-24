/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lewa.crazychapter11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.AutoFocusMoveCallback;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.Log;
import android.media.MediaPlayer;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.BitmapFactory;
import android.content.DialogInterface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class CaptureImage extends Activity {
	SurfaceView sView;
	SurfaceHolder surfaceHolder;
	int screenWidth, screenHeight;
	Camera camera;
	boolean isPreview = false;
	private final String CAMERA_CRAZY_TAG = "camera_crazy_tag";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.camera_main_layout);

		// 获取窗口管理器
		WindowManager wm = getWindowManager();
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		Log.i(CAMERA_CRAZY_TAG, "CaptureImage Oncreate init :screenWidth="
				+ screenWidth + " \n screenHeight=" + screenHeight);
		Log.i(CAMERA_CRAZY_TAG,
				"==== > Environment.getExternalStorageDirectory() = "
						+ Environment.getExternalStorageDirectory());
		sView = (SurfaceView) findViewById(R.id.sView);
		sView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder = sView.getHolder();
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				initCamera();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				Log.i(CAMERA_CRAZY_TAG, "surfaceDestroyed: camera=" + camera
						+ " \n isPreview=" + isPreview);
				if (camera != null) {
					if (isPreview) {
						camera.stopPreview();
					}
					camera.release();
					camera = null;
					isPreview = false;
				}
			}
		});
	}

	private void initCamera() {
		Log.i(CAMERA_CRAZY_TAG, " oooo initCamera: isPreview=" + isPreview);
		if (!isPreview) {
			camera = Camera.open(0);
			camera.setDisplayOrientation(90);
		}

		if (camera != null && !isPreview) {
			try {
				Camera.Parameters parameters = camera.getParameters();
				parameters.setPreviewSize(screenWidth, screenWidth);
				parameters.setPreviewFpsRange(4, 10);
				parameters.setPictureFormat(ImageFormat.JPEG);
				parameters.set("jpeg-quality", 85);
				parameters.setPictureSize(screenWidth, screenHeight);
				camera.setPreviewDisplay(surfaceHolder);
				camera.startPreview();

			} catch (Exception e) {
				e.printStackTrace();
			}
			isPreview = true;
		}
	}

	public void capture(View source) {
		Log.i(CAMERA_CRAZY_TAG, "capture: camera=" + camera);
		if (camera != null) {
			camera.autoFocus(autoFocusCallback);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		Log.i(CAMERA_CRAZY_TAG, "onTouchEvent: !!!");
		capture(null);
		return true;
	}

	AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {
		@Override
		public void onAutoFocus(boolean success, Camera camera) {
			if (success) {
				camera.takePicture(new ShutterCallback() {
					public void onShutter() {
						// /按下快门瞬间执行代码
					}
				}, new PictureCallback() {
					public void onPictureTaken(byte[] data, Camera c) {
						// /此处代码可以决定是否需要保存原始照片信息
					}
				}, myJpegCallback);
			}
		}
	};

	PictureCallback myJpegCallback = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
					data.length);
			View saveDialog = getLayoutInflater().inflate(R.layout.camera_save,
					null);
			final EditText photoName = (EditText) saveDialog
					.findViewById(R.id.phone_name);
			ImageView show = (ImageView) saveDialog
					.findViewById(R.id.camera_show);
			show.setImageBitmap(bm);
			new AlertDialog.Builder(CaptureImage.this)
					.setView(saveDialog)
					.setPositiveButton("保存",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									/*
									 * File file = new
									 * File(Environment.getExternalStorageDirectory
									 * (), photoName.getText().toString()+".jpg"
									 * );
									 */
									File file = new File(
											"/storage/sdcard0/photo", photoName
													.getText().toString()
													+ ".jpg");
									FileOutputStream outStream = null;
									try {
										outStream = new FileOutputStream(file);
										bm.compress(CompressFormat.JPEG, 100,
												outStream);
										outStream.close();

									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}).setNegativeButton("取消", null).show();

			camera.stopPreview();
			camera.startPreview();
			isPreview = true;
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(CAMERA_CRAZY_TAG, "onDestroy: !!xxx");
	}

}
