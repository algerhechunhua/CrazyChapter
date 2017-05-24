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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.telephony.PhoneStateListener;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

public class TelephonyStatus extends Activity {
	ListView showView;
	String[] statusNames;
	ArrayList<String> statusValues = new ArrayList<String>();
	ArrayList<String> statusNameList = new ArrayList<String>();
	private final String TAG_TELEPHONY = "tag_telephony_status";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telephony_layout);
		TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		statusNames = getResources().getStringArray(R.array.statusNames);

		String[] simState = getResources().getStringArray(R.array.simState);

		String[] phoneType = getResources().getStringArray(R.array.phoneType);
		
		try{

		statusValues.add(tManager.getDeviceId());
		statusValues.add(tManager.getDeviceSoftwareVersion() != null ? tManager
				.getDeviceSoftwareVersion() : "未知");
		statusValues.add(tManager.getNetworkOperator());
		statusValues.add(tManager.getNetworkOperatorName());
		statusValues.add(phoneType[tManager.getPhoneType()]);
		statusValues.add(tManager.getCellLocation() != null ? tManager
				.getCellLocation().toString() : "未知位置");
		statusValues.add(tManager.getSimCountryIso());
		statusValues.add(tManager.getSimSerialNumber());
		statusValues.add(simState[tManager.getSimState()]);
		statusValues.add(tManager.getLine1Number());
		for(int i=0;i<statusValues.size();i++){
			statusNameList.add("simStatus"+String.valueOf(i+1));
		}
		
		Log.i("status_name_list","statusNameList="+statusNameList);
		Log.i("status_name_list","statusNames="+statusNames);
		Log.i(TAG_TELEPHONY,
				"tManager.getPhoneType()=" + tManager.getPhoneType()
						+ "\n tManager.getSimState()=" + tManager.getSimState());

		showView = (ListView) findViewById(R.id.telephony_status_show);
		ArrayList<Map<String, String>> status = new ArrayList<Map<String, String>>();

		for (int i = 0; i < statusValues.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("name", statusNames[i]);
			map.put("name", statusNameList.get(i));
			map.put("value", statusValues.get(i));
			status.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, status, R.layout.telephony_result,
				new String[] { "name", "value" }, new int[] { R.id.result_name,
						R.id.result_value });
		showView.setAdapter(adapter);
		}catch(ArrayIndexOutOfBoundsException e){
			Log.i(TAG_TELEPHONY,"error for outof ");
		}
		
		///通话监听
		PhoneStateListener listener = new PhoneStateListener(){
			@Override
			public void onCallStateChanged(int state,String number){
				switch(state){
				case TelephonyManager.CALL_STATE_IDLE:
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					break;
				case TelephonyManager.CALL_STATE_RINGING:
					OutputStream os = null;
					try{
						os = openFileOutput("phoneList",MODE_APPEND);
					}catch(FileNotFoundException e){
						e.printStackTrace();
					}
					
					PrintStream ps = new PrintStream(os);
					ps.println(new Date()+"来电："+number);
					ps.close();
					break;
				default:
					break;
				}
				
				super.onCallStateChanged(state, number);
			}
		};
		
		tManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
}
