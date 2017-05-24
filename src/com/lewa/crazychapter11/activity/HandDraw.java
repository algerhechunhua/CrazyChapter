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

import android.graphics.Color;
import java.io.IOException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import android.util.Log;
import android.graphics.EmbossMaskFilter;
import android.graphics.BlurMaskFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.util.Log;

public class HandDraw extends Activity {

	EmbossMaskFilter emboss;
	BlurMaskFilter blur;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handdraw_layout);
		emboss = new EmbossMaskFilter(new float[]{1.5f,1.5f,1.5f},0.6f,6.0f,4.2f);
		blur = new BlurMaskFilter(8,BlurMaskFilter.Blur.NORMAL);
		Log.i("HandDraw","HandDraw onCreate entry!! ");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.my_menu,menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem mi){
		DrawView dv = (DrawView)findViewById(R.id.view_draw);
//		DrawView dv = new DrawView(this);
		switch(mi.getItemId()){
		case R.id.red:
			dv.paint.setColor(Color.RED);
			mi.setCheckable(true);
			break;
		case R.id.green:
			dv.paint.setColor(Color.GREEN);
			mi.setCheckable(true);
			break;
		case R.id.blue:
			dv.paint.setColor(Color.BLUE);
			mi.setCheckable(true);
			break;
		case R.id.width_1:
			dv.paint.setStrokeWidth(1);
			break;
		case R.id.width_3:
			dv.paint.setStrokeWidth(3);
			break;
		case R.id.width_5:
			dv.paint.setStrokeWidth(5);
			break;
		case R.id.blur:
			dv.paint.setMaskFilter(blur);
			break;
		case R.id.emboss:
			dv.paint.setMaskFilter(emboss);
			break;	
		}
		return true;
	}
}
