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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.app.Fragment;

//import android.app.Fragment;

public class BookDetailFragment extends Fragment {
	public static final String ITEM_ID="item_id";
	BookContent.Book book;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getArguments().containsKey(ITEM_ID)){
			book=BookContent.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
		}
	}

	@Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_book_detail,container,false);
		if(book != null){
			 ((TextView) rootView.findViewById(R.id.book_title)).setText(book.title);
			 ((TextView) rootView.findViewById(R.id.book_desc)).setText(book.desc);
		}
		return rootView;
	}
}
