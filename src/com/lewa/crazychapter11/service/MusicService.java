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
import android.content.Context;
import android.os.Binder;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.util.Log;
import android.os.IBinder;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.content.res.AssetFileDescriptor;

public class MusicService extends Service {
	private final String CRAZY_BIND_SERVICE_TAG = "crazy_music_service";
	MyMusicReceiver serviceReceiver;
	AssetManager am;
	MediaPlayer mPlayer;
	String[] musics = new String[] { "jiangnanstyle.mp3", "xiaoapple.mp3",
			"yulong.mp3" };
	int status = 0x11;
	int current = 0;

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(CRAZY_BIND_SERVICE_TAG, "crazy_music_service is onBind  !!");
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(CRAZY_BIND_SERVICE_TAG, "crazy_music_service is onCreate !!");
		am = getAssets();
		serviceReceiver = new MyMusicReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(BroadcastMain.CTL_ACTION);
		registerReceiver(serviceReceiver, filter);
		
		mPlayer = new MediaPlayer();
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				Log.i(CRAZY_BIND_SERVICE_TAG, "onCompletion is xxxxxxxxoooooo current="+current);
				current++;
				if (current >= 3) {
					current = 0;
				}

				Intent sendIntent = new Intent(BroadcastMain.UPDATE_ACTION);
				sendIntent.putExtra("current", current);
				sendBroadcast(sendIntent);
				prepareAndPlay(musics[current]);
			}
		});

		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(CRAZY_BIND_SERVICE_TAG,
				"crazy_music_service is onStartCommand !!");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(CRAZY_BIND_SERVICE_TAG, "crazy_music_service is onDestroy !!");
	}
	
	private class MyMusicReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			int control = intent.getIntExtra("control", -1);
			
			Log.i(CRAZY_BIND_SERVICE_TAG, "MyMusicReceiver : control="+control);
			Log.i(CRAZY_BIND_SERVICE_TAG, "MyMusicReceiver : musics="+musics);
			Log.i(CRAZY_BIND_SERVICE_TAG, "MyMusicReceiver : current="+current);
			switch (control) {
			case 1:
				if (status == 0x11) {
					prepareAndPlay(musics[current]);
					status = 0x12;
				} else if (status == 0x12) {
					mPlayer.pause();
					status = 0x13;
				} else if (status == 0x13) {
					mPlayer.start();
					status = 0x12;
				}
				break;
			case 2:
				if (status == 0x12 || status == 0x13) {
					mPlayer.stop();
					status = 0x11;
				}
				break;
				
			case 3:///next
				current++;
				if (current >= 3) {
					current = 0;
				}
				
				prepareAndPlay(musics[current]);
				status = 0x12;
				break;
			case 4:///prev
				current--;
				if (current < 0) {
					current = 2;
				}
				
				prepareAndPlay(musics[current]);
				status = 0x12;
				break;
			}
			
			Intent sendIntent = new Intent(BroadcastMain.UPDATE_ACTION);
			sendIntent.putExtra("update", status);
			sendIntent.putExtra("current", current);
			sendBroadcast(sendIntent);
		}
	}
	
	
	private void prepareAndPlay(String music){
		try{
			AssetFileDescriptor afd = am.openFd(music);
			Log.i(CRAZY_BIND_SERVICE_TAG, "prepareAndPlay : current="+current);
			mPlayer.reset();
			mPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			mPlayer.prepare();
			mPlayer.start();
		}catch(Exception e){
			e.printStackTrace();
			Log.i(CRAZY_BIND_SERVICE_TAG, "prepareAndPlay : error xxxxxxxxxxxx");
		}
	}
}
