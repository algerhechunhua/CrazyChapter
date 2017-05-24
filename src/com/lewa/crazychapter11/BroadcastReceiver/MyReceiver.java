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
import android.app.ActivityManager;
import java.util.List;
import android.app.ActivityManager.RunningServiceInfo;

public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {

		Intent intentICat = new Intent();
		intentICat.setAction("org.crazyit.service.CRAZY_AIDL_SERVICE");

		switch (intent.getAction()) {
			case "org.crazyit.action.CRAZY_BROADCAST":
			Toast.makeText(
				context,
				"接收到的Intent的Action为：" + intent.getAction() + " \n 消息内容是："
				+ intent.getStringExtra("msg"), Toast.LENGTH_LONG)
			.show();
			break;
			case "org.crazyit.action.CRAZY_ORDER_BROADCAST":
			Toast.makeText(
				context,
				"有序广播，11xxxx111 ：" + intent.getAction() + " \n 消息内容是："
				+ intent.getStringExtra("msg"), Toast.LENGTH_LONG)
			.show();
			
			Bundle bundle = new Bundle();
			bundle.putString("first", "何纯华");
			setResultExtras(bundle);
			
			break;	

			case "com.lewa.alarm.test":
			Log.i("algerheAlarm","MyReceiver  : now in time="+System.currentTimeMillis());
			break;

			case "android.provider.Telephony.SECRET_CODE":
			Log.i("algerheSecretCode","MyReceiver 1111 : 5169="+System.currentTimeMillis());
			break;	

			case Intent.ACTION_USER_PRESENT:
			Log.i("crazy_bind_service","MyReceiver:unlock message ACTION_USER_PRESENT recieve !!");
			Log.i("crazy_bind_service","isServiceWork="+isServiceWork(context,"com.lewa.crazychapter11.AidlService"));
			Log.i("crazy_bind_service","isRMSServiceWork="+isServiceWork(context,"com.RMS.service.TrackService"));
			if(!isServiceWork(context,"com.lewa.crazychapter11.AidlService")){
				context.startService(intentICat);
			}
			break;		

			default:
			break;
		}
	}


	public boolean isServiceWork(Context mContext, String serviceName) {  
		boolean isWork = false;  
		ActivityManager myAM = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);  
		List<RunningServiceInfo> myList = myAM.getRunningServices(0xff);  
		if (myList.size() <= 0) {  
			return false;  
		}  
		for (int i = 0; i < myList.size(); i++) {  
			String mName = myList.get(i).service.getClassName().toString(); 
			Log.i("RunningServiceName","mName="+mName);
			if (mName.equals(serviceName)) {  
				isWork = true;  
				break;  
			}  
		}  
		return isWork;  
	} 
}
