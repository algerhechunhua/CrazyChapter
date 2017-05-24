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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.BroadcastReceiver;
import java.text.SimpleDateFormat;
import java.util.Date;

public class shutdownReceiver extends BroadcastReceiver {
	private final String TAG_SYSTEM_INFO = "shutdown_log";
	@Override
	public void onReceive(Context context, Intent intent) {		


		String action = intent.getAction();

		if (Intent.ACTION_BOOT_COMPLETED.equals(action)){
			Log.i(TAG_SYSTEM_INFO,"BOOT_COMPLETED,You can add code here to do something after boot !!");
		}
		else if ("com.lewa.alarm.test".equals(action)) {
			Log.i("algerheAlarm","shutdownReceiver alarm message now in time="+System.currentTimeMillis());
		}
		else if ("android.provider.Telephony.SECRET_CODE".equals(action)) {
			Toast.makeText(context, "recieve code 123789",
				Toast.LENGTH_LONG).show();
			Log.i(TAG_SYSTEM_INFO,"you recieve code here !! 123789");
		}else if("android.intent.action.ACTION_SHUTDOWN".equals(action)){
			
			writeCurrTime();
			/////can't receive SECRET_CODE message in this receiver
		// }else if("android.provider.Telephony.SECRET_CODE".equals(action)){
			
		// 	Log.i("algerheSecretCode","shutdownReceiver: 5169="+System.currentTimeMillis());			
		}else if("android.intent.action.SCREEN_OFF".equals(action)){
			Intent intentICatOff = new Intent();
			intentICatOff.setAction("org.crazyit.service.CRAZY_AIDL_SERVICE");
			Log.i("crazy_bind_service","shutdownReceiver:ACTION_SCREEN_OFF recieve !!");
			// context.startService(intentICatOff);
		}
	}
	public void writeCurrTime(){
		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    HH:mm:ss     ");       
		Date    			curDate      =   new    Date(System.currentTimeMillis());//获取当前时间       
		String    			str          =    formatter.format(curDate);

		Log.i(TAG_SYSTEM_INFO,"recieve shutown message !! time : "+str);

		for (int ntime=0;ntime<5 ;ntime++ ) {
			MainActivity.SharedShutdownTimeWrite(str);
		}
	}

	
}
