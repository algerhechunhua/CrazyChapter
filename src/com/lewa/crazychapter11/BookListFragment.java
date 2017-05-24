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
import android.support.v4.app.Fragment;
import android.app.ListFragment;
import android.app.Activity;
import android.widget.ListView;

//import android.app.Fragment;

public class BookListFragment extends ListFragment {
	private Callbacks mCallbacks;
	public interface Callbacks{
		public void onItemSelected(Integer id);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(),android.R.layout.simple_list_item_activated_1,
				android.R.id.text1,BookContent.ITEMS));
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		if(!(activity instanceof Callbacks)){
			throw new IllegalStateException("BookListFragment 所在的Activity必须实现Callbacks接口！");
		}
		
		mCallbacks=(Callbacks)activity;
	}
	
	@Override
	public void onDetach(){
		super.onDetach();
		mCallbacks=null;
	}
	
	@Override
	public void onListItemClick(ListView listView,View view,int position,long id){
		super.onListItemClick(listView,view,position,id);
		mCallbacks.onItemSelected(BookContent.ITEMS.get(position).id);
	}
	
	public void setActivateOnItemClick(boolean activateOnItemClick){
		getListView().setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE:ListView.CHOICE_MODE_NONE);
	}
}
