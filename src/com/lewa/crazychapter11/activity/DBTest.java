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

public class DBTest extends Activity {
	SQLiteDatabase db;
	Button bn = null;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_base_layout);

		db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
				+ "/my.db3", null);
		listView = (ListView) findViewById(R.id.db_show);
		try{
			Cursor cursor = db.rawQuery("select*from news_inf", null);
			inflateList(cursor);
		//}catch(Exception e){
			//Log.i("DBTest","error db load");
		}catch(SQLiteException se) {
			Log.i("DBTest","SQLiteException error db load");
		}catch(IndexOutOfBoundsException e){
//			e.printStackTrace();
			Log.i("DBTest","IndexOutOfBoundsException error db load");
		}
		
		bn = (Button) findViewById(R.id.btn_ok);

		bn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				String title = ((EditText) findViewById(R.id.db_title)).getText()
						.toString();
				String content = ((EditText) findViewById(R.id.db_content))
						.getText().toString();
				try {
					Log.i("DBTest","insert 1111");
					insertData(db,title,content);
					Cursor cursor = db.rawQuery("select*from news_inf", null);
					inflateList(cursor);
				} catch (SQLiteException se) {
					Log.i("DBTest","insert 22222");
					db.execSQL("create table news_inf(_id integer"
							+ " primary key autoincrement,"
							+ " news_title varchar(50),"
							+ " news_content varchar(255))");
					insertData(db, title, content);
					Cursor cursor = db.rawQuery("select*from news_inf", null);
					inflateList(cursor);
				}
			}
		});
	}

	private void insertData(SQLiteDatabase db, String title, String content) {
		db.execSQL("insert into news_inf values(null,?,?)", new String[] {
				title, content });

		Log.i("DBTest", "insertData 5555");
	}

	private void inflateList(Cursor cursor) {
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(DBTest.this,
				R.layout.db_line, cursor, new String[] { "news_title",
						"news_content" }, new int[] { R.id.my_title,
						R.id.my_content },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		listView.setAdapter(adapter);
		Log.i("DBTest", "inflateList 44444");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("DBTest", "onDestroy 3333333");
		if (db != null && db.isOpen()) {
			db.close();
		}
	}
}
