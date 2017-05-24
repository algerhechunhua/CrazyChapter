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

import java.io.File;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadSdCardActivity extends Activity {
	ListView listView;
	TextView textView;
	File currentParent;
	File[] currentFiles;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_sdcard_layout);

		listView = (ListView) findViewById(R.id.list);
		textView = (TextView) findViewById(R.id.path);

		File root = new File("/mnt/sdcard/");
		if (root.exists()) {
			currentParent = root;
			currentFiles = root.listFiles();
			inflateListView(currentFiles);
		}

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (currentFiles[position].isFile())
					return;
				File[] tmp = currentFiles[position].listFiles();
				if (tmp == null || tmp.length == 0) {
					Toast.makeText(ReadSdCardActivity.this,
							"当前路径不可访问或该路径下没有文件", Toast.LENGTH_SHORT).show();
				} else {
					currentParent = currentFiles[position];
					currentFiles = tmp;
					inflateListView(currentFiles);
				}
			}
		});
		
		Button parent = (Button) findViewById(R.id.btn_parent);
		parent.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View source){
				try{
					if(!currentParent.getCanonicalPath().equals("/mnt/sdcard")){
						currentParent = currentParent.getParentFile();
						currentFiles = currentParent.listFiles();
						inflateListView(currentFiles);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
   
	private void inflateListView(File[] files) {
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < files.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			if (files[i].isDirectory()) {
				listItem.put("icon", R.drawable.folder);
			} else {
				listItem.put("icon", R.drawable.file);
			}
			listItem.put("fileName", files[i].getName());
			listItems.add(listItem);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.simple_line, new String[] { "icon", "fileName" }, new int[] {
						R.id.line_icon, R.id.line_file_name });// /modified here remember

		listView.setAdapter(simpleAdapter);

		try {
			textView.setText("当前路径为：" + currentParent.getCanonicalPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
