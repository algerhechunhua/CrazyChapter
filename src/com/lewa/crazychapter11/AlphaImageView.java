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
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.ClipDrawable;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import java.util.TimerTask;
import android.util.AttributeSet;
import android.graphics.Canvas;

public class AlphaImageView extends ImageView {

	private int alphaDelta = 0;
	private int curAlpha = 0;
	private final int SPEED = 300;
	// */
	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x1233) {
				curAlpha += alphaDelta;
				if (curAlpha >= 254) {
					curAlpha = 0;
				}
				Log.i("AlphaImageView", "1111 AlphaImageView curAlpha=" + curAlpha);
				Log.i("AlphaImageView", "2222 AlphaImageView alphaDelta=" + alphaDelta);
				AlphaImageView.this.setAlpha(curAlpha);
			}
		}
	};

	public AlphaImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.AlphaImageView);
		int duration = typedArray
				.getInt(R.styleable.AlphaImageView_duration, 0);
		alphaDelta = 255 * SPEED / duration;

		Log.i("AlphaImageView", "AlphaImageView duration=" + duration);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		this.setAlpha(curAlpha);
		super.onDraw(canvas);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0x1233;

				if (curAlpha >= 255) {
					timer.cancel();
				} else {
					handler.sendMessage(msg);
				}
			}
		}, 0, SPEED);
	}
	// */
}
