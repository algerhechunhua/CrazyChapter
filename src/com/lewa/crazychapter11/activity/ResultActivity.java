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
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.Toast;
import android.widget.SimpleAdapter;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_layout);

		ListView listView = (ListView) findViewById(R.id.result_show);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();

		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = (List<Map<String, String>>) data
				.getSerializable("data");

		Log.i("ResultActivity", "list=" + list);
		Log.i("ResultActivity", "list.isEmpty()=" + list.isEmpty());
		if (list.isEmpty()) {
			Toast.makeText(ResultActivity.this, "没有查到您需要的单词！",
					Toast.LENGTH_LONG).show();
			return;
		}

		SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, list,
				R.layout.line_result, new String[] { "word", "detail","key" },
				new int[] { R.id.result_word, R.id.result_detail,R.id.result_key });
		listView.setAdapter(adapter);
		
	}

}
