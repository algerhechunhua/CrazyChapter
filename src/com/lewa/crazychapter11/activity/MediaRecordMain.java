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

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.content.res.TypedArray;
import android.util.Log;
import android.media.MediaPlayer;

public class MediaRecordMain extends Activity implements OnClickListener {
	ImageButton record, stop;
	File soundFile;
	private final String RECORD_FILE_PATH = "/mnt/sdcard/soundrecord.amr";
	MediaRecorder mRecorder;
	MediaPlayer mMediaplayer;
	private final String TAG_MEDIA_RECORDER = "media_recorder";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediarecorder_main_layout);
		record = (ImageButton) findViewById(R.id.imgbtn_record_start);
		stop = (ImageButton) findViewById(R.id.imgbtn_record_stop);
		record.setOnClickListener(this);
		stop.setOnClickListener(this);

		initRecordPlay();

	}

	private void initRecordPlay() {
			

			Button btn_record_play = (Button) findViewById(R.id.btn_record_play);
			btn_record_play.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						mMediaplayer = new MediaPlayer();
						mMediaplayer.setDataSource(RECORD_FILE_PATH);
						
						mMediaplayer.prepare();
						mMediaplayer.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

			Button btn_record_stop = (Button) findViewById(R.id.btn_record_stop);
			btn_record_stop.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mMediaplayer != null) {
						mMediaplayer.stop();
					}
				}
			});
	}

	@Override
	public void onDestroy() {
		if (mRecorder != null && soundFile != null && soundFile.exists()) {
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View source) {
		switch (source.getId()) {
		case R.id.imgbtn_record_start:
			if (!Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {
				Toast.makeText(MediaRecordMain.this, "SD卡不存在，请插入SD卡！",
						Toast.LENGTH_LONG).show();
				return;
			}

			try {

				// Log.i(TAG_MEDIA_RECORDER,"Environment.getExternalStorageState() ="+Environment.getExternalStorageState()+
				// " \n android.os.Environment.MEDIA_MOUNTED="+android.os.Environment.MEDIA_MOUNTED);
				// Log.i(TAG_MEDIA_RECORDER,"Environment.getExternalStorageDirectory().getCanonicalFile()="+
				// Environment.getExternalStorageDirectory().getCanonicalFile());

				// soundFile = new
				// File(Environment.getExternalStorageDirectory().getCanonicalFile()+"/sound.amr");
				soundFile = new File(RECORD_FILE_PATH);
				mRecorder = new MediaRecorder();
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				Log.i(TAG_MEDIA_RECORDER, "soundFile.getAbsolutePath() = "
						+ soundFile.getAbsolutePath());
				mRecorder.setOutputFile(soundFile.getAbsolutePath());
				mRecorder.prepare();
				mRecorder.start();

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.imgbtn_record_stop:
			if (mRecorder != null && soundFile != null && soundFile.exists()) {
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;
			}
			break;
		}
	}
}
