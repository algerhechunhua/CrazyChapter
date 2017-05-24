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
import android.content.IntentFilter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class BroadcastMain extends Activity implements OnClickListener {
	TextView title, author;
	ImageButton imgbtn_play, imgbtn_stop;
	Button btn_next,btn_prev;
	ActivityReceiver activityReceiver;
	int update,current;
	public static final String CTL_ACTION = "org.crazyit.action.CTL_ACTION";
	public static final String UPDATE_ACTION = "org.crazyit.action.UPDATE_ACTION";

	private final String CRAZY_BIND_SERVICE_TAG = "crazy_music_service";

	int status = 0x11;
	String[] titleStrs = new String[] { "江南Style", "小苹果", "玉龙" };
	String[] authorStrs = new String[] { "鸟叔", "筷子兄弟", "未知艺术家" };
	private void setMusicInfo(Intent intent){
		if(intent !=null){
		 update = intent.getIntExtra("update", -1);
		 current = intent.getIntExtra("current", -1);
		}else{
			update = -1;
			current = -1;
		}

		if (current >= 0) {
			title.setText(titleStrs[current]);
			author.setText(authorStrs[current]);
		} else {
			title.setText("未知歌曲");
			author.setText("未知艺术家");
		}
	}

	public class ActivityReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction() == UPDATE_ACTION) {
				setMusicInfo(intent);

				Log.i(CRAZY_BIND_SERVICE_TAG, "ActivityReceiver update="
						+ update + " \n current=" + current);

				switch (update) {
				case 0x11:
					imgbtn_play.setImageResource(R.drawable.status_play);
					status = 0x11;
					break;
				case 0x12:
					imgbtn_stop.setImageResource(R.drawable.status_pause);
					status = 0x12;
					break;
				case 0x13:
					imgbtn_play.setImageResource(R.drawable.status_play);
					status = 0x13;
					break;
				}
			}
		}
	}

	@Override
	public void onClick(View source) {
		Intent intent = new Intent("org.crazyit.action.CTL_ACTION");
		Log.i(CRAZY_BIND_SERVICE_TAG,
				"onClick source.getId()=" + source.getId());
		switch (source.getId()) {
		case R.id.imgbtn_music_play:
			intent.putExtra("control", 1);
			break;
		case R.id.imgbtn_music_stop:
			intent.putExtra("control", 2);
			break;
		case R.id.btn_music_next:
			intent.putExtra("control", 3);
			break;
		case R.id.btn_music_prev:
			intent.putExtra("control", 4);
			break;
		}

		sendBroadcast(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast_main_layout);

		imgbtn_play = (ImageButton) this.findViewById(R.id.imgbtn_music_play);
		imgbtn_stop = (ImageButton) this.findViewById(R.id.imgbtn_music_stop);
		btn_next = (Button) this.findViewById(R.id.btn_music_next);
		btn_prev = (Button) this.findViewById(R.id.btn_music_prev);
		title = (TextView) findViewById(R.id.music_title);
		author = (TextView) findViewById(R.id.music_author);
		imgbtn_play.setOnClickListener(this);
		imgbtn_stop.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		btn_prev.setOnClickListener(this);

		activityReceiver = new ActivityReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(UPDATE_ACTION);
		registerReceiver(activityReceiver, filter);

		// Intent intent = new Intent(BroadcastMain.this, MusicService.class);
		Intent intentMusic = new Intent();
		intentMusic.setAction("org.crazyit.service.CRAZY_MUSIC_SERVICE");
		startService(intentMusic);
		
		///init infomation for music
		setMusicInfo(null);

		// /发送普通广播
		Button btn_send_broadcast = (Button) findViewById(R.id.btn_send_broadcast);
		btn_send_broadcast.setOnClickListener(
			new OnClickListener() {
			@Override
			public void onClick(View v) {
				//*/
				Intent intent = new Intent();
				intent.setAction("org.crazyit.action.CRAZY_BROADCAST");
				intent.putExtra("msg", "简单消息");
				sendBroadcast(intent);
				//*/
			}
		});

		// 有序广播
		Button btn_send_order_broadcast = (Button) findViewById(R.id.btn_send_order_broadcast);
		btn_send_order_broadcast.setOnClickListener(
			new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("org.crazyit.action.CRAZY_ORDER_BROADCAST");
				intent.putExtra("msg", "简单有序广播消息");

				sendOrderedBroadcast(intent, null);
			}
		});

	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(activityReceiver);
	}
}
