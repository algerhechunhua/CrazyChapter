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

public class MyFirstService extends Service {
	private final String CRAZY_BIND_SERVICE_TAG = "crazy_first_service";

	@Override
	public IBinder onBind(Intent arg0){
		Log.i(CRAZY_BIND_SERVICE_TAG,"MyFirstService:Service is onBind  !!");
		return null;
	}
	
	@Override 
	public void onCreate(){
		Log.i(CRAZY_BIND_SERVICE_TAG,"MyFirstService:Service is onCreate !!");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent,int flags, int startId){
		Log.i(CRAZY_BIND_SERVICE_TAG,"MyFirstService:Service is onStartCommand !!");
		return START_STICKY;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i(CRAZY_BIND_SERVICE_TAG,"MyFirstService:Service is onDestroy !!");
	}
}
