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
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.LauncherActivity;
import android.os.Bundle;

public class OtherActivity extends LauncherActivity {
	String[] names = {"设置程序参数","查看星际兵种"};
	Class<?>[] clazzs = {PreferenceActivityTest.class,ExpandableListActivityTest.class};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice,names);
		setListAdapter(adapter);
		
		/*/
		Intent intent=getIntent();
		String name = intent.getStringExtra("name");
		Bundle extras = getIntent().getExtras();
		String name1 = extras.getString("name");
		if(name1 !=null && name !=null){
		Toast.makeText(OtherActivity.this, "name:"+name+"   name1:"+name1, Toast.LENGTH_LONG)
				.show();
		}
		/*/
	}	
   
	@Override
	public Intent intentForPosition(int position){
		Bundle data = new Bundle();
		data.putString("name","hechunhua");
		Intent intent=new Intent(OtherActivity.this,clazzs[position]);
		intent.putExtras(data);
		
		return intent;
	}
   
}

