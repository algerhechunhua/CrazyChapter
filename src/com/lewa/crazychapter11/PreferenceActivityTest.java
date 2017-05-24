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
import android.widget.Button;
import android.widget.Toast;
import android.view.ViewGroup;
import android.preference.PreferenceActivity;
import android.preference.PreferenceActivity.Header;
import android.os.Bundle;

import java.util.List;

import android.preference.PreferenceFragment;

public class PreferenceActivityTest extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (hasHeaders()) {
			Button button = new Button(this);
			button.setText("设置操作");
			setListFooter(button);
		}
		
		Intent intent=getIntent();
		String name = intent.getStringExtra("name");
		Bundle extras = getIntent().getExtras();
		String name1 = extras.getString("name");
		Toast.makeText(PreferenceActivityTest.this, "name:"+name+"   name1:"+name1, Toast.LENGTH_LONG)
				.show();
		
		
	}

	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preference_headers, target);
	}

	public static class Prefs1Fragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}

	public static class Prefs2Fragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.display_prefs);
			String website = getArguments().getString("website");
			////
			Intent intent=getActivity().getIntent();
			String name = intent.getStringExtra("name");
			Bundle extras = getActivity().getIntent().getExtras();
			String name1 = extras.getString("name");
			Toast.makeText(getActivity(), "网站域名是：" + website+"name:"+name+"name1:"+name1, Toast.LENGTH_LONG)
					.show();
		}
	}

}
