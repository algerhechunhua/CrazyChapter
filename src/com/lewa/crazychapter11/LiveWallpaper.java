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

import java.util.Random;

import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.service.wallpaper.WallpaperService.Engine;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public final class LiveWallpaper extends WallpaperService {
	private Bitmap heart;

	@Override
	public Engine onCreateEngine() {
		heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
		return new MyEngine();
	}

	class MyEngine extends Engine {
		private boolean mVisible;
		private float mTouchX = -1;
		private float mTouchY = -1;
		private int count = 1;
		private int originX = 50;
		private int originY = 50;
		private Paint mPaint = new Paint();
		Handler mHandler = new Handler();
		private final Runnable drawTarget = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				drawFrame();
			}
		};

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
			mPaint.setARGB(76, 0, 0, 255);
			mPaint.setAntiAlias(true);
			mPaint.setStyle(Paint.Style.FILL);
			setTouchEventsEnabled(true);
			Log.i("livewallpaper","onCreate  enter here");
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			mHandler.removeCallbacks(drawTarget);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			mVisible = visible;
			if (visible) {
				drawFrame();
			} else {
				mHandler.removeCallbacks(drawTarget);
			}
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset, float xStep,
				float yStep, int xPixels, int yPixels) {
			drawFrame();
		}

		@Override
		public void onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				mTouchX = event.getX();
				mTouchY = event.getY();
			} else {
				mTouchX = -1;
				mTouchY = -1;
			}
			super.onTouchEvent(event);
		}

		private void drawFrame() {
			final SurfaceHolder holder = getSurfaceHolder();
			Canvas c = null;
			try {
				c = holder.lockCanvas();
				if (c != null) {
					c.drawColor(0xffffffff);
					drawTouchPoint(c);
					mPaint.setAlpha(76);
					c.translate(originX, originY);
					for (int i = 0; i < count; i++) {
						c.translate(80, 0);
						c.scale(0.95f, 0.95f);
						c.rotate(20f);
						c.drawRect(0, 0, 150, 75, mPaint);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (c != null) {
					holder.unlockCanvasAndPost(c);
				}
			}

			mHandler.removeCallbacks(drawTarget);

			if (mVisible) {
				count++;
				if (count >= 50) {
					Random rand = new Random();
					count = 1;
					originX += (rand.nextInt(60) - 30);
					originY += (rand.nextInt(60) - 30);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			mHandler.postDelayed(drawTarget, 100);
			}
		}

		private void drawTouchPoint(Canvas c) {
			if (mTouchX >= 0 && mTouchY >= 0) {
				mPaint.setAlpha(255);
				c.drawBitmap(heart, mTouchX, mTouchY, mPaint);
			}
		}
	}
}
