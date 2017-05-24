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

public class BindService extends Service {
	private int count;
	private boolean quit;
	private MyBinder binder = new MyBinder();
	private final String CRAZY_BIND_SERVICE_TAG = "crazy_bind_service";

	public class MyBinder extends Binder {
		public int getCount() {
			return count;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(CRAZY_BIND_SERVICE_TAG, "BindService:Service is Binded");
		return binder;
	}

	@Override 
	public void onCreate(){
		super.onCreate();
		Log.i(CRAZY_BIND_SERVICE_TAG,"BindService:Service is Created !");
		new Thread(){
			@Override
			public void run(){
				while(!quit){
					try{
						Thread.sleep(1000);
					}catch (InterruptedException e){
						e.printStackTrace();
					}
					count++;
					Log.i("while_tag","BindService:Service bind count="+count);
				}
			}
		}.start();
		
	}
	@Override
	public boolean onUnbind(Intent intent){
		Log.i(CRAZY_BIND_SERVICE_TAG,"BindService:Service is Unbinded !!");
		return true;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		this.quit = true;
		Log.i(CRAZY_BIND_SERVICE_TAG,"BindService:Service is onDestroy !!");
	}
}
