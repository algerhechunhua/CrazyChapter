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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import android.graphics.RectF;
import android.graphics.LinearGradient;

public class MyView extends View {
	Bitmap drawImage;
	List<Bitmap> bmDrawImage;
	List<Integer> resourceValues = new ArrayList<Integer>();

	public MyView(Context context, AttributeSet set) {
		super(context, set);
		initSourceImage(context);
		Log.i("crazy_MyView_tag","00000 initSourceImage");
	}

	public MyView(Context context) {
		super(context);
		initSourceImage(context);
		Log.i("crazy_MyView_tag","11111 initSourceImage");
	}

	public void initSourceImage(Context context) {
		Integer drawImage;
		bmDrawImage = new ArrayList<Bitmap>();
		try {
			Field[] drawableFields = R.drawable.class.getFields();
			for (Field field : drawableFields) {
				// 如果该Field的名称以p_开头
				if (field.getName().indexOf("plane") != -1) {
					drawImage = field.getInt(R.drawable.class);
					Bitmap bm = BitmapFactory.decodeResource(context.getResources(),
							drawImage);
					bmDrawImage.add(bm);
					Log.i("crazy_MyView_tag","field.getName()="+field.getName());
					Log.i("crazy_MyView_tag","drawImage="+drawImage);
				}
			}
		} catch (Exception e) {
			Log.i("crazy_MyView_tag","enter exception error  !!");
			return ;
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);

		canvas.drawCircle(40, 40, 30, paint);

		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.RED);
		canvas.drawCircle(120, 40, 30, paint);

		RectF re2 = new RectF(90, 200, 150, 270);
		canvas.drawRoundRect(re2, 15, 15, paint);

		// /设置渐变色
		Shader mShader = new LinearGradient(0, 0, 40, 60, new int[] {
				Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW }, null,
				Shader.TileMode.REPEAT);
		paint.setShader(mShader);
		paint.setShadowLayer(45, 10, 10, Color.GRAY);
		canvas.drawCircle(200, 40, 30, paint);
		// /绘画反射搜索出的固定图片
		if (bmDrawImage !=null && bmDrawImage.size() != 0) {
			int ix=0;
			for(Bitmap tpbm:bmDrawImage){
				ix += 200;
			canvas.drawBitmap(tpbm, 300+ix, 40, null);
			}
		}
	}
	// */
}
