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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent {
	public static class Book {
		public Integer id;
		public String title;
		public String desc;

		public Book(Integer id, String title, String desc) {
			this.id = id;
			this.title = title;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return title;
		}
	}
	
	public static List<Book> ITEMS=new ArrayList<Book>();
	public static Map<Integer,Book> ITEM_MAP=new HashMap<Integer,Book>();
	static {
		addItem(new Book(1,"疯狂java讲义","一本全面。深入的java学习图书，已作为高校选修教材"));
		addItem(new Book(2,"疯狂Android讲义","全面用的实力"));
		addItem(new Book(3,"lewa讲义丛书","乐哇人的卡哇伊书记"));
	}
	
	private static void addItem(Book book){
		ITEMS.add(book);
		ITEM_MAP.put(book.id,book);
	}
}
