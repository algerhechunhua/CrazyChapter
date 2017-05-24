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

import android.view.MotionEvent;
import android.util.Log;
import android.widget.Button;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class MyButton extends Button {
	public MyButton(Context context, AttributeSet set) {
		super(context, set);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		Log.v("algerhe1", "the onKeyDown in MyButton keyCode="+keyCode);
		Log.v("algerhe1", "the onKeyDown in MyButton KeyEvent.KEYCODE_BACK="+KeyEvent.KEYCODE_BACK);
		Log.v("algerhe1", "the onKeyDown in MyButton event="+event);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			Log.v("algerhe1", "onTouch evnt up ");
			break;
		case MotionEvent.ACTION_DOWN:
			Log.v("algerhe1", "onTouch evnt event down");
			 break;
		case MotionEvent.ACTION_CANCEL:
			Log.v("algerhe1", "onTouch evnt cancel here");
			break;
		}
		return true;
	}
}
