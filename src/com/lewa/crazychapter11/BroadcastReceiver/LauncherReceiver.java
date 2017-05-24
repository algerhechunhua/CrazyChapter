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

public class LauncherReceiver extends BroadcastReceiver {
	private final String TAG_SYSTEM_INFO = "crazy_system_log";
	@Override
	public void onReceive(Context context, Intent intent) {
		

		String action = intent.getAction();

        Intent intentICat = new Intent();
        intentICat.setAction("org.crazyit.service.CRAZY_AIDL_SERVICE");

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)){
           Log.i(TAG_SYSTEM_INFO,"BOOT_COMPLETED,You can add code here to do something after boot !!");
				
		//Intent tIntent = new Intent();
		//tIntent.setAction("org.crazyit.service.ALARM_CHANGEWALLPAPER_SERVICE");
		//context.startService(tIntent);

        // Intent i = new Intent(context, MainActivity.class);
        // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // context.startActivity(i);

        ///start AidlService.class        
        Log.i("crazy_aidl_service","LauncherReceiver: start AidlService.class");
        context.startService(intentICat);
 
        }
        else if ("android.provider.Telephony.SECRET_CODE".equals(action)) {
            Toast.makeText(context, "recieve code 123789",
								Toast.LENGTH_LONG).show();
            Log.i(TAG_SYSTEM_INFO,"you recieve code here !! 123789");
            Log.i("crazy_aidl_service","LauncherReceiver: stop AidlService.class");
            context.stopService(intentICat);
        }else if("android.intent.action.ACTION_SHUTDOWN".equals(action)){
        	Log.i(TAG_SYSTEM_INFO,"recieve shutown message !!");
        }else if("android.intent.action.ACTION_SHUTDOWN".equals(action)){
        	Log.i(TAG_SYSTEM_INFO,"11111 recieve shutown message !!");
        }
    }
}
