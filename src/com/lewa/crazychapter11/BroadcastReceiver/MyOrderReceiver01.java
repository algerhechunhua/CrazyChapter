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

public class MyOrderReceiver01 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		switch (intent.getAction()) {
		case "org.crazyit.action.CRAZY_BROADCAST":
			Toast.makeText(
					context,
					"接收到的Intent的Action为：" + intent.getAction() + " \n 消息内容是："
							+ intent.getStringExtra("msg"), Toast.LENGTH_LONG)
					.show();
			break;
		case "org.crazyit.action.CRAZY_ORDER_BROADCAST":
			///get result		
			Bundle bundle=getResultExtras(true);
			String first = bundle.getString("first");
			
			///set result
			Bundle bundle2 = new Bundle();
			bundle2.putString("first", "何纯华");
			bundle2.putString("second", "杨湄");
			setResultExtras(bundle2);
			
			Toast.makeText(
					context,
					"有序广播，，222222：" + intent.getAction() + " \n 第一次存入的消息"
							+ first, Toast.LENGTH_LONG)
					.show();
//			abortBroadcast();
			break;	
		default:
			break;
		}
	}
}
