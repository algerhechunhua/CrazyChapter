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
import android.widget.Button;
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
import android.hardware.Camera;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.media.MediaPlayer;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.BitmapFactory;
import android.content.DialogInterface;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.media.MediaRecorder;
import android.graphics.ImageFormat;
import android.media.CamcorderProfile;

public class RecordVideo extends Activity implements OnClickListener{
	Button record,stop;
	File videoFile;
	MediaRecorder mRecorder;
	Camera camera;
	SurfaceView sView;
	private boolean isRecording = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.recordvideo_layout);
		
		record = (Button) this.findViewById(R.id.btn_recordvideo_play);
		stop = (Button) this.findViewById(R.id.btn_recordvideo_stop);
		stop.setEnabled(false);
		
		record.setOnClickListener(this);
		stop.setOnClickListener(this);
		sView = (SurfaceView) this.findViewById(R.id.recordvidio_surface_view);
		sView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		sView.getHolder().setFixedSize(320, 280);
		///设置该组件让屏幕不会自动关闭
		sView.getHolder().setKeepScreenOn(true);
	}
	
	@Override
	public void onClick(View source){
		switch(source.getId()){
		case R.id.btn_recordvideo_play:
			if(!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
				Toast.makeText(RecordVideo.this, "SD卡不存在，请插入！", Toast.LENGTH_LONG);
				return;
			}
			
			try{
				Log.i("crazy_recordvideo_tag","Environment.getExternalStorageDirectory().getCanonicalFile() ="
			                                  +Environment.getExternalStorageDirectory().getCanonicalFile());
				
				videoFile = new File(Environment.getExternalStorageDirectory().getCanonicalFile()+"/myvideo.mp4");
				mRecorder = new MediaRecorder();
				 mRecorder.reset();

				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

				mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
				mRecorder.setVideoSize(320, 280);
				mRecorder.setVideoFrameRate(4);
				mRecorder.setOutputFile(videoFile.getAbsolutePath());
				mRecorder.setPreviewDisplay(sView.getHolder().getSurface());

				mRecorder.prepare();
				mRecorder.start();
				
				record.setEnabled(false);
				stop.setEnabled(true);
				isRecording = true;
			}catch(Exception e){
				e.printStackTrace();
			}
			break;
		case R.id.btn_recordvideo_stop:
			if(isRecording){
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;
				record.setEnabled(true);
				stop.setEnabled(false);
			}
			break;	
		}
	}
}

