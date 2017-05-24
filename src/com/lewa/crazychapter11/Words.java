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

import android.provider.BaseColumns;
import android.net.Uri;

public final class Words {
	public static final String AUTHORITY = "org.crazyit.providers.dictprovider";
	
	public static final class Word implements BaseColumns{
		public final static String _ID = "_id";
		public final static String WORD = "word";
		public final static String DETAIL = "detail";
		public final static String KEY = "key";
		public final static Uri DICT_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/words");
		public final static Uri WORD_CONTENT_RUI = Uri.parse("content://"+AUTHORITY+"/word");
	}
}
