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
import android.database.sqlite.SQLiteDatabase;
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
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.widget.SimpleCursorAdapter;
import android.util.Log;

public class Dict extends Activity {
	MyDatabaseHelper dbHelper;
	Button insert = null;
	Button search = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_base_helper_layout);

        dbHelper = new MyDatabaseHelper(this,"myDict.db3",1);
        
        insert = (Button) findViewById(R.id.btn_insert);
        search = (Button) findViewById(R.id.btn_search);

        insert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				String word = ((EditText) findViewById(R.id.word)).getText().toString();
				String detail = ((EditText) findViewById(R.id.detail)).getText().toString();
				
				insertData(dbHelper.getReadableDatabase(),word,detail);
				Toast.makeText(Dict.this, "添加生词成功", Toast.LENGTH_SHORT).show();
				
			}
		});
        
        search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				String key = ((EditText)findViewById(R.id.key)).getText().toString();
				
				Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
						"select*from dict where word like ? or detail like ?",
						new String[]{"%"+key+"%"+"%"+key+"%"}
						);
				Bundle data = new Bundle();
				data.putSerializable("data",converCursorToList(cursor));
				
				Intent intent = new Intent(Dict.this,ResultActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});
	}
	
	protected ArrayList<Map<String,String>> converCursorToList(Cursor cursor){
		ArrayList<Map<String,String>> result= new ArrayList<Map<String,String>>();
		while (cursor.moveToNext()){
			Map<String,String> map = new HashMap<String,String>();
			map.put("word", cursor.getString(1));
			map.put("detail", cursor.getString(2));
			result.add(map);
		}
		return result;
	}
	
	private void insertData(SQLiteDatabase db,String word,String detail){
		db.execSQL("insert into dict values(null,?,?)",new String[] {word,detail});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("Dict", "onDestroy ****");
		if (dbHelper != null ) {
			dbHelper.close();
		}
	}
}
