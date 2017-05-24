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
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentProvider;
import android.net.Uri;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;

public class DictProviderTest extends ContentProvider {
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int WORDS = 1;
	private static final int WORD = 2;
	private MyDatabaseHelper dbOpenHelper;

	static {
		matcher.addURI(Words.AUTHORITY, "words", WORDS);
		matcher.addURI(Words.AUTHORITY, "words/#", WORD);
	}

	@Override
	public boolean onCreate() {
		dbOpenHelper = new MyDatabaseHelper(this.getContext(), "myDict.db3", 1);
		Log.i("DictProviderTest", "onCreate enter 000");
		return true;
	}

	@Override
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
		case WORDS:
			return "vnd.android.cursor.dir/org.crazyit.dict";
		case WORD:
			return "vnd.android.cursor.item/org.crazyit.dict";
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}
	}
	
	@Override
	public Cursor query(Uri uri,String[] projection,String where,String[] whereArgs,String sortOrder){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Log.i("DictProviderTest", "query enter 111 uri="+uri);
		switch (matcher.match(uri)){
		case WORDS:
			return db.query("dict", projection, where, whereArgs, null, null, sortOrder);
		case WORD:
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" +id;
			if(where != null && !"".equals(where)){
				whereClause = whereClause +" and" + where;
			}
			return db.query("dict", projection, whereClause, whereArgs, null, null, sortOrder);
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);			
		}
	}
	
	@Override
	public Uri insert(Uri uri,ContentValues values){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Log.i("DictProviderTest", "insert enter 222 values="+values);
		switch (matcher.match(uri)){
		case WORDS:
			long rowId = db.insert("dict",Words.Word._ID,values);
			
			Log.i("DictProviderTest", "insert rowId="+rowId);
			if(rowId > 0){
				Uri wordUri = ContentUris.withAppendedId(uri, rowId);
				getContext().getContentResolver().notifyChange(wordUri,null);
				return wordUri;
			}
			break;
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}
		return null;
	}
	
	@Override 
	public int update(Uri uri,ContentValues values,String where,String[] whereArgs){
		 SQLiteDatabase db =dbOpenHelper.getWritableDatabase();
		 Log.i("DictProviderTest", "update enter 333 uri="+uri);
		int num = 0;
		switch (matcher.match(uri)){
		case WORDS:
			num = db.update("dict", values, where, whereArgs);
			break;
		case WORD:
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" +id;
			if(where != null && !where.equals("")){
				whereClause = whereClause + " and" + where;
			}
			num = db.update("dict", values, whereClause, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri,null);
		return num;
	}
	
	@Override
	public int delete(Uri uri,String where,String[] whereArgs){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		int num = 0;
		Log.i("DictProviderTest", "delete enter 444 uri="+uri);
		switch (matcher.match(uri)){
		case WORDS:
			num = db.delete("dict", where, whereArgs);
			break;
		case WORD:
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" +id;
			if(where != null && !where.equals("")){
				whereClause = whereClause + " and" + where;
			}
			num = db.delete("dict",whereClause, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri,null);
		return num;
	}
}
