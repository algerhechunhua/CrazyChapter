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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	final String CREATE_TABLE_SQL="create table dict(_id integer primary "
                                 + " key autoincrement ,"
			                     + " word,"
			                     + " detail,"
                                 + " key)";
	
    public MyDatabaseHelper(Context context,String name, int version){
    	super(context,name,null,version);
    	Log.i("MyDatabaseHelper","MyDatabaseHelper xigouhanshu 000");
    }
    
    @Override
	public void onCreate(SQLiteDatabase db) {
    	Log.i("MyDatabaseHelper","onCreate entry 111");
    	db.execSQL(CREATE_TABLE_SQL);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    	Log.i("MyDatabaseHelper","oldVersion ="+oldVersion+"--> newVersion="+newVersion);
    }
}
