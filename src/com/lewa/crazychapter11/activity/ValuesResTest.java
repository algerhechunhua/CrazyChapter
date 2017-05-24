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
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.content.res.TypedArray;

public class ValuesResTest extends Activity {
	
	String[] texts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.values_res);
		texts = getResources().getStringArray(R.array.string_arr);
		BaseAdapter ba = new BaseAdapter(){
			@Override
			public int getCount(){
				return texts.length;
			}
			
			@Override
			public Object getItem(int position){
				return texts[position];
			}
			
			@Override
			public long getItemId(int position){
				return position;
			}
			
			@Override
			public View getView(int position, View convertView,ViewGroup parent){
				TextView text= new TextView(ValuesResTest.this);
				Resources res = ValuesResTest.this.getResources();
				text.setWidth((int)res.getDimension(R.dimen.cell_width));
				text.setHeight((int) res.getDimension(R.dimen.cell_height));
				text.setText(texts[position]);
//				text.setBackgroundResource(colorIds[position]);
				TypedArray icons=res.obtainTypedArray(R.array.plain_arr);
				text.setBackgroundDrawable(icons.getDrawable(position));
				text.setTextSize(20);
				text.setTextSize(getResources().getInteger(R.integer.font_size));
				return text;
			}
		};
		GridView grid = (GridView)findViewById(R.id.grid01);
		grid.setAdapter(ba);
	}
}

