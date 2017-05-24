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

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class GestureZoom extends Activity implements OnGestureListener {
	GestureDetector detector;
	ImageView imageView;
	Bitmap bitmap;
	int width, height;
	float currentScale = 1;
	Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_layout);
		
		Log.i("GestureZoom","Enter onCreate !! 000");

		detector = new GestureDetector(this, this);
		imageView = (ImageView) findViewById(R.id.gesture_image);
		matrix = new Matrix();
		bitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.plane_img);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		imageView.setImageBitmap(BitmapFactory.decodeResource(
				this.getResources(), R.drawable.plane_img));
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		Log.i("GestureZoom","Enter onTouchEvent !! 111 ");
		return detector.onTouchEvent(me);
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
			float velocityX, float velocityY) {
		Log.i("GestureZoom","Enter onFling !! 222 ");
		Log.i("onFling","velocityX="+velocityX+"   \n velocityY="+velocityY);
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityX = velocityX < -4000 ? -4000 : velocityX;
		currentScale += currentScale * velocityX / 4000.0f;
		currentScale = currentScale > 0.01 ? currentScale : 0.01f;
		matrix.reset();
		matrix.setScale(currentScale, currentScale, 160, 200);
		BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();
		if (!tmp.getBitmap().isRecycled()) {
			tmp.getBitmap().recycle();
			System.gc();
		}
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		imageView.setImageBitmap(bitmap2);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		Log.i("GestureZoom","Enter onDown !! 333");
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		Log.i("GestureZoom","Enter onLongPress !! 444 ");
	}

	@Override
	public boolean onScroll(MotionEvent event1, MotionEvent event2,
			float distanceX, float distanceY) {
		Log.i("GestureZoom","Enter onScroll !! 555 ");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		Log.i("GestureZoom","Enter onShowPress !!666");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		Log.i("GestureZoom","Enter onSingleTapUp !! 777");
		return false;
	}
}
