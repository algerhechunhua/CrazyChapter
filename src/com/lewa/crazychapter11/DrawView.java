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
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.RectF;
import android.graphics.LinearGradient;

public class DrawView extends View {
	float preX;
	float preY;
	private Path path;
	public Paint paint = null;
	final int VIEW_WIDTH=320;
	final int VIEW_HEIGHT = 480;
	Bitmap cacheBitmap = null;
	Canvas cacheCanvas = null;
	
	private void initSource(){
		cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT,Config.ARGB_8888);
    	cacheCanvas = new Canvas();
    	path = new Path();
    	cacheCanvas.setBitmap(cacheBitmap);
    	paint = new Paint(Paint.DITHER_FLAG);
    	paint.setColor(Color.RED);
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeWidth(1);
    	paint.setAntiAlias(true);
    	paint.setDither(true);
	}
	
    public DrawView(Context context,AttributeSet set){
    	super(context,set);
    	initSource();
    }
    public DrawView(Context context){
    	super(context);
    	initSource();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event){
    	float x = event.getX();
    	float y = event.getY();
    	switch (event.getAction()){
    	case MotionEvent.ACTION_DOWN:
    		path.moveTo(x, y);
    		preX = x;
    		preY = y;
    		break;
    	case MotionEvent.ACTION_MOVE:
    		path.quadTo(preY, preX, x, y);
    		preX = x;
    		preY = y;
    		break;
    	case MotionEvent.ACTION_UP:
    		Log.i("DrawView","onTouchEvent ACTION_UP 0000000000 !");    		
    		cacheCanvas.drawPath(path, paint);
    		path.reset();
    		break;
    	}
    	invalidate();
    	return true;
    }
	@Override
	public void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
		Paint bmpPaint = new Paint();
		Log.i("DrawView","onDraw enter xxxxxxxxx !");
		//canvas.drawBitmap(cacheBitmap, 0, 0,bmpPaint);
//		canvas.drawPath(path, paint);
	}
	// */
}
