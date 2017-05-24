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
import java.util.Timer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Rect;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.graphics.PixelFormat;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SurfaceViewTest extends Activity {
	private SurfaceHolder holder;
	private Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.surface_view_layout);
		paint = new Paint();
		SurfaceView surface = (SurfaceView) findViewById(R.id.surface_show);
		holder = surface.getHolder();
		//*/
		//surface.setLayerType(View.LAYER_TYPE_NONE, null);
		surface.setZOrderOnTop(true);// 设置画布 背景透明
		holder.setFormat(PixelFormat.TRANSLUCENT);
		//*/
		
		
		

		holder.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3) {
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				Canvas canvas = holder.lockCanvas();
				Bitmap back = BitmapFactory.decodeResource(
						SurfaceViewTest.this.getResources(), R.drawable.mm02);
				canvas.drawBitmap(back, 0, 0, null);
				holder.unlockCanvasAndPost(canvas);
				holder.lockCanvas(new Rect(0, 0, 0, 0));
				holder.unlockCanvasAndPost(canvas);
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {

			}
		});

		surface.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View source, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int cx = (int) event.getX();
					int cy = (int) event.getY();

					Canvas canvas = holder.lockCanvas(new Rect(cx - 50,
							cy - 50, cx + 50, cy + 50));
					
					//*/ shared prefe test start
					SharedPreferences preferences = getSharedPreferences("crazyit", MODE_PRIVATE);
					SharedPreferences.Editor editor;
					
					String time = preferences.getString("time", null);
					int randNum = preferences.getInt("random", 0);
					if (time != null) {
						Log.i("crazychater8", "time=" + time);
					}
					Log.i("crazychater8", "randNum=" + randNum);
					String result = time == null ? "您暂时未输入" : "写入时间：" + time
							+ "\n 上次生成的随机数为：" + randNum;
					Toast.makeText(SurfaceViewTest.this, result, Toast.LENGTH_SHORT)
							.show();
					//*/
					/////shared prefe test end
					
					/// draw background of update area
					paint.setColor(Color.BLUE);
					canvas.drawRect(cx - 50, cy - 50, cx+50, cy+50, paint);
					///
					canvas.save();
					canvas.rotate(30, cx, cy);
					paint.setColor(Color.RED);
					canvas.drawRect(cx - 40, cy - 40, cx, cy, paint);
					canvas.restore();
					canvas.rotate(330, cx, cy);
					paint.setColor(Color.GREEN);
					canvas.drawRect(cx, cy, cx + 40, cy + 40, paint);
					holder.unlockCanvasAndPost(canvas);
				}
				return false;
			}
		});
	}
}
