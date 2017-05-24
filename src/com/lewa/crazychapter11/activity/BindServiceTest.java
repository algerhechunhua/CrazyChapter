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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.app.Service;
import android.os.Binder;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.content.ComponentName;
import android.view.View.OnClickListener;
import android.os.IBinder;
import android.view.View;
import android.content.Intent;
import android.os.RemoteException;

public class BindServiceTest extends Activity {
	Button bind, unbind, getServiceStatus, btnStart, btnStop,btn_my_service,btn_myintent_service,btn_get_service_status;
	EditText edit_get_color,edit_get_weight;
	BindService.MyBinder binder;
	private ICat catService;

	private final String CRAZY_BIND_SERVICE_TAG = "crazy_bind_service";

	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(CRAZY_BIND_SERVICE_TAG, "-- Service Connected --");
			binder = (BindService.MyBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(CRAZY_BIND_SERVICE_TAG, "== Service Disconnected ==");
		}
	};

	private ServiceConnection conn3 = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(CRAZY_BIND_SERVICE_TAG, "-- Service Connected --");
			catService = ICat.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(CRAZY_BIND_SERVICE_TAG, "== Service Disconnected ==");

			catService = null;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_test_layout);
		Log.i(CRAZY_BIND_SERVICE_TAG, "BindServiceTest:== Service onCreate ==");
		btnStart = (Button) findViewById(R.id.btn_service_start);
		btnStop = (Button) findViewById(R.id.btn_service_stop);

		bind = (Button) findViewById(R.id.service_bind);
		unbind = (Button) findViewById(R.id.service_unbind);
		getServiceStatus = (Button) findViewById(R.id.service_status);

		btn_my_service = (Button) findViewById(R.id.btn_my_service);
		btn_myintent_service = (Button) findViewById(R.id.btn_myintent_service);

		btn_get_service_status = (Button) findViewById(R.id.btn_get_service_status);
		edit_get_color = (EditText) findViewById(R.id.edit_get_color);
		edit_get_weight = (EditText) findViewById(R.id.edit_get_weight);

		final Intent intent1 = new Intent();
		intent1.setAction("org.crazyit.service.FIRST_SERVICE");

		final Intent intent2 = new Intent(BindServiceTest.this,BindService.class);
//		intent2.setAction("org.crazyit.service.BIND_SERVICE");

//		Intent intent3 = new Intent(BindServiceTest.this,AidlService.class);
		Intent intent3 = new Intent();
		intent3.setAction("org.crazyit.service.CRAZY_AIDL_SERVICE");
		bindService(intent3, conn3, Service.BIND_AUTO_CREATE);

		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				startService(intent1);
			}
		});

		btnStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				stopService(intent1);
			}
		});

		bind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				bindService(intent2, conn, Service.BIND_AUTO_CREATE);
			}
		});

		unbind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				unbindService(conn);
			}
		});

		getServiceStatus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				if (binder != null) {
					Toast.makeText(BindServiceTest.this,
							"Service 的count值为：" + binder.getCount(),
							Toast.LENGTH_LONG).show();
				}
			}
		});

		btn_my_service.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				Intent intent = new Intent(BindServiceTest.this,MyService.class);
				startService(intent);
			}
		});

		btn_myintent_service.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				Intent intent = new Intent(BindServiceTest.this,MyIntentService.class);
				startService(intent);
			}
		});

		btn_get_service_status.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				try{
					edit_get_color.setText(catService.getColor());
					edit_get_weight.setText(catService.getWeight()+"");
				}catch(RemoteException e){
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		this.unbindService(conn3);
	}
}
