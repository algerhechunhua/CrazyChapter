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

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import android.util.Log;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ClipDrawable;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.content.res.AssetManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.view.View;

public class MoveBack extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MoveView(this));
	}

	class MoveView extends View {
		final int BACK_HEIGHT = 1152;
		private Bitmap back;
		private Bitmap plane;
		final int WIDTH = 2049;
		final int HEIGHT = 400;
		private int startY = BACK_HEIGHT - HEIGHT;

		public MoveView(Context context) {
			super(context);
			back = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.back_img);
			plane = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.plane_img);

			Log.i("MoveView", "back.width=" + back.getWidth()
					+ " \n back.height=" + back.getHeight() + "plane.width ="
					+ plane.getWidth() + "plane.height=" + plane.getHeight());

			// */
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 0x123) {
						if (startY <= 0) {
							startY = BACK_HEIGHT - HEIGHT;
						} else {
							startY -= 3;
						}
					}
					invalidate();
				}
			};

			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					handler.sendEmptyMessage(0x123);
				}
			}, 0, 100);
			// */
		}

		@Override
		public void onDraw(Canvas canvas) {
			if (startY <= 0) {
				startY = BACK_HEIGHT - HEIGHT;
			}
			Bitmap bitmap2 = Bitmap
					.createBitmap(back, 0, startY, WIDTH, HEIGHT);
			canvas.drawBitmap(bitmap2, 0, 0, null);
			canvas.drawBitmap(plane, 0, 0, null);
		}
	}
}
