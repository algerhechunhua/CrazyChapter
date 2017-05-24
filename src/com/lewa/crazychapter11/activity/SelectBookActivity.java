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
import android.app.Activity;

public class SelectBookActivity extends Activity implements BookListFragment.Callbacks {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_book_twopane);
	}	
   
	@Override
	public void onItemSelected(Integer id){
		Bundle arguments = new Bundle();
		arguments.putInt(BookDetailFragment.ITEM_ID, id);
		BookDetailFragment fragment=new BookDetailFragment();
		fragment.setArguments(arguments);
		getFragmentManager().beginTransaction().replace(R.id.book_detail_container, fragment).commit();
	}
}

