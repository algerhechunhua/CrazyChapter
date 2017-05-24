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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnKeyListener;
import android.content.Context;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PlaneView extends View {	
	public float currentX;
	public float currentY;
	Bitmap plane;
	
	public PlaneView(Context context){
		super(context);
		plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
		setFocusable(true);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		Paint p = new Paint();
		canvas.drawBitmap(plane, currentX, currentY,p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		this.currentX=event.getX();
		this.currentY=event.getY();
		
		Log.v("algerhe", "onTouchEvent  xxx:planeView.currentX="+event.getX()+"  planeView.currentY="+event.getY());
		this.invalidate();
		return true;
	}
}

