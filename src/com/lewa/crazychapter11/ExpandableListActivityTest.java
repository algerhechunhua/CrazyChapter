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

import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class ExpandableListActivityTest extends ExpandableListActivity{
	private String[] armTypes=new String[]{
			"神族兵种","虫族兵种","人族兵种"	
	};
	
	private String[][] arms=new String[][]{
			{"狂战士","龙骑士","黑暗圣堂","电兵"},
			{"小狗","刺蛇","飞龙","自爆飞机"},
			{"机枪兵","护士","幽灵"},
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
        
		ExpandableListAdapter adapter = new BaseExpandableListAdapter(){
			int[] logos=new int[]{
				R.drawable.libai,
				R.drawable.tiger,
				R.drawable.qingzhao
			};
			
			@Override
			public Object getChild(int groupPosition,int childPosition){
				return arms[groupPosition][childPosition];
			}
			
			@Override
			public long getChildId(int groupPosition,int childPosition){
				return childPosition;
			}
			
			@Override
			public int getChildrenCount(int groupPosition){
				return arms[groupPosition].length;
			}
			
			private TextView getTextView(){
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,164);
				TextView textView = new TextView(ExpandableListActivityTest.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				textView.setPadding(136, 10, 0, 10);
				textView.setTextSize(20);
				return textView;
			}
			
			@Override
			public View getChildView(int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent){
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition,childPosition).toString());
				return textView;
			}
			
			@Override
			public Object getGroup(int groupPosition){
				return armTypes[groupPosition];
			}
			
			@Override
			public int getGroupCount(){
				return armTypes.length;
			}
			
			@Override
			public long getGroupId(int groupPosition){
				return groupPosition;
			}
			
			@Override
			public View getGroupView(int groupPosition,boolean isExpanded,View convertView,ViewGroup parent){
				LinearLayout ll=new LinearLayout(ExpandableListActivityTest.this);
				ll.setOrientation(0);
				ImageView logo = new ImageView(ExpandableListActivityTest.this);
				logo.setImageResource(logos[groupPosition]);
				ll.addView(logo);
				TextView textView = getTextView();
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);
				return ll;
			}
			
			@Override
			public boolean isChildSelectable(int groupPosition,int childPosition){
				return true;
			}
			
			@Override
			public boolean hasStableIds(){
				return true;
			}
		};
		
		setListAdapter(adapter);
		
		getExpandableListView().setOnChildClickListener(new OnChildClickListener(){
			@Override public boolean onChildClick(ExpandableListView parent,View source,int groupPosition,int childPosition,long id){
				Intent intent = getIntent();
				intent.putExtra("armType",arms[groupPosition][childPosition]);
				ExpandableListActivityTest.this.setResult(0,intent);
				ExpandableListActivityTest.this.finish();
				return false;
			}
		}
		);
	}	
   	
}

