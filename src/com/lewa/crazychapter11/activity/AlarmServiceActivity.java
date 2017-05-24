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

public class AlarmServiceActivity extends Activity {
	MediaPlayer alarmMusic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		alarmMusic = MediaPlayer.create(this, R.raw.yulong);
		alarmMusic.setLooping(true);
		alarmMusic.start();

		new AlertDialog.Builder(AlarmServiceActivity.this)
				.setTitle("闹钟")
				.setMessage("闹钟响了，GO GO GO！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						alarmMusic.stop();
						AlarmServiceActivity.this.finish();
					}
				})
				.show();
	}
}
