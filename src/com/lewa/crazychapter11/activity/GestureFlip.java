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
import android.widget.ViewFlipper;
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
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.view.View;

public class GestureFlip extends Activity implements OnGestureListener {
	GestureDetector detector;
	ViewFlipper flipper;
	Animation[] animations = new Animation[4];
	final int FLIP_DISTANCE = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_flip_layout);
		Log.i("GestureZoom", "Enter onCreate !! 000");

		detector = new GestureDetector(this, this);
		flipper = (ViewFlipper) this.findViewById(R.id.flipper);
		flipper.addView(addImageView(R.drawable.mm01));
		flipper.addView(addImageView(R.drawable.fangzi));
		flipper.addView(addImageView(R.drawable.mm02));
		flipper.addView(addImageView(R.drawable.hushui01));
		flipper.addView(addImageView(R.drawable.dahai));

		animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);

	}

	private View addImageView(int resId) {
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		return imageView;
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		Log.i("GestureZoom", "Enter onTouchEvent !! 111 ");
		return detector.onTouchEvent(me);
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
			float velocityX, float velocityY) {
		Log.i("GestureZoom", "Enter onFling !! 222 ");
		Log.i("onFling", "velocityX=" + velocityX + "   \n velocityY="
				+ velocityY);

		if (event1.getX() - event2.getX() > FLIP_DISTANCE) {
			flipper.setInAnimation(animations[1]);
			flipper.setOutAnimation(animations[2]);
			flipper.showPrevious();
			return true;

		} else if (event2.getX() - event1.getX() > FLIP_DISTANCE) {
			flipper.setInAnimation(animations[3]);
			flipper.setOutAnimation(animations[2]);
			flipper.showNext();
			return true;
		}
		return false;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		Log.i("GestureZoom", "Enter onDown !! 333");
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		Log.i("GestureZoom", "Enter onLongPress !! 444 ");
	}

	@Override
	public boolean onScroll(MotionEvent event1, MotionEvent event2,
			float distanceX, float distanceY) {
		Log.i("GestureZoom", "Enter onScroll !! 555 ");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		Log.i("GestureZoom", "Enter onShowPress !!666");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		Log.i("GestureZoom", "Enter onSingleTapUp !! 777");
		return false;
	}
}
