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

import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.os.Binder;
import android.content.Intent;
import android.util.Log;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.IntentService;
import android.content.IntentFilter;

public class AidlService extends Service {
	private final String CRAZY_BIND_SERVICE_TAG = "crazy_aidl_service";
	
	private CatBinder catBinder;
	Timer timer = new Timer();
	private String color;
	private double weight;
	private LockReceiver mlockReceiver;
	
	String[] colors = new String[]{
		"红色",
		"黄色",
		"黑色"
	};
	
	double[] weights = new double[]{
		2.3,
		3.1,
		1.58
	};
	
	public class CatBinder extends ICat.Stub{
		@Override
		public String getColor() throws RemoteException{
			return color;
		}
		
		@Override
		public double getWeight() throws RemoteException{
			return weight;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(CRAZY_BIND_SERVICE_TAG, "MyService:Service is onBind  !!");
		return catBinder;
	}



	@Override
	public void onCreate() {
		Log.i(CRAZY_BIND_SERVICE_TAG, "MyService:Service is onCreate !!");
		super.onCreate();		

		////start receiver
		IntentFilter mIntentFilter = new IntentFilter("android.intent.action.ACTION_SHUTDOWN");
						 mIntentFilter.addAction("android.intent.action.SCREEN_ON");
						 mIntentFilter.addAction("android.intent.action.SCREEN_OFF");
						 mIntentFilter.addAction("android.intent.action.USER_PRESENT");
			mlockReceiver = new LockReceiver();
			registerReceiver(mlockReceiver ,mIntentFilter);
		///end receiver	
		
		catBinder = new CatBinder();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				int rand = (int)(Math.random() * 3);
				color = colors[rand];
				weight = weights[rand];
				
				Log.i("AidlService_is_running_log", "AidlService: rand="+rand);
			}
		}, 0,800);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(CRAZY_BIND_SERVICE_TAG, "MyService:Service is onStartCommand !!");
		return START_STICKY;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent,startId);
		Log.i(CRAZY_BIND_SERVICE_TAG, "MyService:Service is onStart !!");
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(CRAZY_BIND_SERVICE_TAG, "MyService:Service is onDestroy !!");
		timer.cancel();
		unregisterReceiver(mlockReceiver);
	}
}
