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

import android.app.Service;
import android.os.Binder;
import android.content.Intent;
import android.util.Log;
import android.os.IBinder;
import android.app.IntentService;

public class MyIntentService extends IntentService {
	private final String CRAZY_BIND_SERVICE_TAG = "crazy_my_service";

	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(CRAZY_BIND_SERVICE_TAG,
				"MyIntentService: Service is onStartCommand !!");
		long endTime = System.currentTimeMillis() + 5 * 1000;
		Log.i(CRAZY_BIND_SERVICE_TAG, "onStart");
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		Log.i(CRAZY_BIND_SERVICE_TAG, "--- MyIntentService 耗时任务完成 ---");

	}
}
