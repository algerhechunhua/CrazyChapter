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

import java.io.IOException;
import java.util.Timer;

import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.content.res.AssetManager;
import android.content.res.AssetFileDescriptor;
import java.util.TimerTask;
import android.util.Log;

public class ClipDrawableTest extends Activity {
	MediaPlayer mediaPlayer1 = null;
	MediaPlayer mediaPlayer2 = null;
	private AnimationDrawable anim = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.CrazyTheme);
		setContentView(R.layout.clip_draw);
		
		///play animation by xml icons
		/*ImageView imageView1 = (ImageView)findViewById(R.id.anim_play);
		anim = (AnimationDrawable) imageView1.getBackground();
		anim.start();*/
		///end play animation

		// */
		ImageView imageView = (ImageView) findViewById(R.id.clip_source);
		final ClipDrawable drawable = (ClipDrawable) imageView.getDrawable();
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x1233) {
					drawable.setLevel(drawable.getLevel() + 200);
				}
			}
		};
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0x1233;
				handler.sendMessage(msg);
				if (drawable.getLevel() >= 10000) {
					timer.cancel();
				}

				if (drawable.getLevel() == 200 && mediaPlayer1 != null) {
//					mediaPlayer1.start();
				}
				if (drawable.getLevel() == 8000 && mediaPlayer1 != null && mediaPlayer2 != null) {
//					mediaPlayer1.stop();
//					mediaPlayer2.start();
				}
			}
		}, 0, 300);
		// */

		// /play Music area
		mediaPlayer1 = MediaPlayer.create(this, R.raw.yulong);
		AssetManager am = getAssets();
		try {
			AssetFileDescriptor afd = am.openFd("jiangnanstyle.mp3");
			mediaPlayer2 = new MediaPlayer();
			mediaPlayer2.setDataSource(afd.getFileDescriptor());
			mediaPlayer2.prepare();

		} catch (IOException e) {
			e.printStackTrace();
			Log.i("MediaPlayerLewa","error here 1111");
		}
		// /end Music
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		anim.stop();
		if (mediaPlayer1 != null)
			mediaPlayer1.stop();
		if (mediaPlayer2 != null)
			mediaPlayer2.stop();
	}
}
