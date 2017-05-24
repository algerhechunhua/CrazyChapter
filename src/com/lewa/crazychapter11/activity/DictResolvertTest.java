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

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.widget.SimpleCursorAdapter;
import android.util.Log;
import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.net.Uri;
import android.database.ContentObserver;
import android.os.Handler;

public class DictResolvertTest extends Activity {
	ContentResolver contentResolver;
	Button insert = null;
	Button search = null;
	Button addPhone = null;
	Button searchPhone = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dict_resolver_layout);

		contentResolver = getContentResolver();
		insert = (Button) findViewById(R.id.btn_resolver_insert);
		search = (Button) findViewById(R.id.btn_resolver_search);

		addPhone = (Button) findViewById(R.id.btn_contact_add);
		searchPhone = (Button) findViewById(R.id.btn_contact_search);

		// /注册数据库变化的监测器
		getContentResolver().registerContentObserver(RawContacts.CONTENT_URI,
				true, new ContactsObserver(new Handler()));

		insert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String word = ((EditText) findViewById(R.id.resolver_word))
						.getText().toString();
				String detail = ((EditText) findViewById(R.id.resolver_detail))
						.getText().toString();
				///add new
				String key = ((EditText) findViewById(R.id.resolver_key))
						.getText().toString();
				
				ContentValues values = new ContentValues();
				values.put(Words.Word.WORD, word);
				values.put(Words.Word.DETAIL, detail);
				values.put(Words.Word.KEY, key);
				Log.i("ResultActivity", "values :" + values);
				contentResolver.insert(Words.Word.DICT_CONTENT_URI, values);

				Toast.makeText(DictResolvertTest.this, "添加生词成功！",
						Toast.LENGTH_LONG).show();
			}
		});

		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String key = ((EditText) findViewById(R.id.resolver_key))
						.getText().toString();

				Cursor cursor = contentResolver.query(
						Words.Word.DICT_CONTENT_URI, null,
						"word like ? or detail like ? or key like ?", new String[] {
								"%" + key + "%", "%" + key + "%", "%" + key + "%" }, null);
				Bundle data = new Bundle();
				data.putSerializable("data", converCursorToList(cursor));
				Intent intent = new Intent(DictResolvertTest.this,
						ResultActivity.class);
				
				Log.i("ResultActivity", "before xxx data :" + data);
				intent.putExtras(data);
				startActivity(intent);
			}
		});

		searchPhone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final ArrayList<String> names = new ArrayList<String>();
				final ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();

				Cursor cursor = getContentResolver().query(
						ContactsContract.Contacts.CONTENT_URI, null, null,
						null, null);

				while (cursor.moveToNext()) {
					String contactId = cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					String name = cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

					names.add(name);

					Cursor phones = getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ "=" + contactId, null, null);

					ArrayList<String> detail = new ArrayList<String>();

					while (phones.moveToNext()) {
						String phoneNumber = phones.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						detail.add("电话号码：" + phoneNumber);
					}

					phones.close();

					Cursor emails = getContentResolver().query(
							ContactsContract.CommonDataKinds.Email.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Email.CONTACT_ID
									+ "=" + contactId, null, null);

					while (emails.moveToNext()) {
						String emailAddress = emails.getString(emails
								.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

						detail.add("邮件地址：" + emailAddress);
					}

					emails.close();
					details.add(detail);
				}
				cursor.close();

				View resultDialog = getLayoutInflater().inflate(
						R.layout.contact_result_layout, null);
				// mView =
				// getLayoutInflater().inflate(R.layout.information_dialog,
				// null);
				ExpandableListView list = (ExpandableListView) resultDialog
						.findViewById(R.id.contact_list);
				ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
					@Override
					public Object getChild(int groupPostion, int childPosition) {
						return details.get(groupPostion).get(childPosition);
					}

					@Override
					public long getChildId(int groupPostion, int childPosition) {
						return childPosition;
					}

					@Override
					public int getChildrenCount(int groupPostion) {
						return details.get(groupPostion).size();
					}

					private TextView getTextView() {
						AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT, 164);

						TextView textView = new TextView(DictResolvertTest.this);
						textView.setLayoutParams(lp);
						textView.setGravity(Gravity.CENTER_VERTICAL
								| Gravity.LEFT);
						textView.setPadding(80, 10, 0, 10);
						textView.setTextSize(20);
						return textView;
					}

					@Override
					public View getChildView(int groupPosition,
							int childPosition, boolean isLastChild,
							View convertView, ViewGroup parent) {
						TextView textView = getTextView();
						textView.setText(getChild(groupPosition, childPosition)
								.toString());
						return textView;
					}

					@Override
					public Object getGroup(int groupPosition) {
						return names.get(groupPosition);
					}

					@Override
					public int getGroupCount() {
						return names.size();
					}

					@Override
					public long getGroupId(int groupPosition) {
						return groupPosition;
					}

					@Override
					public View getGroupView(int groupPosition,
							boolean isExpanded, View convertView,
							ViewGroup parent) {
						TextView textView = getTextView();
						if (getGroup(groupPosition) != null) {
							textView.setText(getGroup(groupPosition).toString());
						}
						return textView;
					}

					@Override
					public boolean isChildSelectable(int groupPostion,
							int childPosition) {
						return true;
					}

					@Override
					public boolean hasStableIds() {
						return true;
					}
				};

				list.setAdapter(adapter);
				new AlertDialog.Builder(DictResolvertTest.this)
						.setView(resultDialog).setPositiveButton("确定", null)
						.show();
			}
		});

		addPhone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = ((EditText) findViewById(R.id.name)).getText()
						.toString();
				String phone = ((EditText) findViewById(R.id.phone)).getText()
						.toString();
				String email = ((EditText) findViewById(R.id.email)).getText()
						.toString();

				ContentValues values = new ContentValues();
				Uri rawContactUri = getContentResolver().insert(
						RawContacts.CONTENT_URI, values);
				long rawContactId = ContentUris.parseId(rawContactUri);
				// /add name
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
				values.put(StructuredName.GIVEN_NAME, name);

				Log.i("contactlog", "RawContacts.CONTENT_URI ="
						+ RawContacts.CONTENT_URI);
				Log.i("contactlog",
						"android.provider.ContactsContract.Data.CONTENT_URI ="
								+ android.provider.ContactsContract.Data.CONTENT_URI);
				Log.i("contactlog", "name :" + values);

				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				// /add phone number
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
				values.put(Phone.NUMBER, phone);
				values.put(Phone.TYPE, Phone.TYPE_MOBILE);
				Log.i("contactlog", "phone number :" + values);
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				// /add email
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
				values.put(Email.DATA, email);
				values.put(Email.TYPE, Email.TYPE_WORK);
				Log.i("contactlog", "email :" + values);
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				Toast.makeText(DictResolvertTest.this, "联系人数据添加成功",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(Words.Word.WORD, cursor.getString(1));
			map.put(Words.Word.DETAIL, cursor.getString(2));
			map.put(Words.Word.KEY, cursor.getString(3));
			result.add(map);
		}
		return result;
	}

	// /监听数据库的变化
	private final class ContactsObserver extends ContentObserver {
		public ContactsObserver(Handler handler) {
			super(handler);
		}

		public void onChange(boolean selfChange) {
			Log.i("ContactsObserver", "selfChange :" + selfChange);
			Toast.makeText(DictResolvertTest.this, "数据库改变了！！！！",
					Toast.LENGTH_LONG).show();
		}
	}
}
