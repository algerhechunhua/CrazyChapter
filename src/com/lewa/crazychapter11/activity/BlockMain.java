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
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Activity;
import android.os.Bundle;
import android.app.PendingIntent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.SmsManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.widget.ListView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.android.internal.telephony.ITelephony;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.os.ServiceManager;
import android.app.Service;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.app.AlarmManager;
import android.app.TimePickerDialog;

import java.util.Calendar;

public class BlockMain extends Activity {
	ArrayList<String> blockList = new ArrayList<String>();
	TelephonyManager tManager;
	CustomPhoneCallListener cpListener;
	SmsManager sManager;
	EditText number, content;
	AudioManager aManager;
	MediaPlayer mPlayer = null;
	Vibrator vibrator;
	Button btn_service_changewallpaper,btn_service_stopwallpaper;
	
	AlarmManager alarmManager;
	//Calendar currentTime = Calendar.getInstance();

	final private String TAG_BLOCK_LIST = "tag_block_list";

	public class CustomPhoneCallListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String number) {
			Log.i(TAG_BLOCK_LIST, "state xxx ="+state);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				Log.i(TAG_BLOCK_LIST, "CALL_STATE_IDLE enter 111");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.i(TAG_BLOCK_LIST, "CALL_STATE_OFFHOOK enter 2222");
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				Log.i(TAG_BLOCK_LIST, "CALL_STATE_RINGING enter 3333");
				Log.i(TAG_BLOCK_LIST, "number =" + number
						+ " \n isBlock(number)=" + isBlock(number));
				if (isBlock(number)) {
					try {
						/*
						 * / Method method = Class.forName(
						 * "android.os.ServiceManager").getMethod( "getService",
						 * String.class); IBinder binder = (IBinder)
						 * method.invoke(null, new Object[] {
						 * Context.TELEPHONY_SERVICE });
						 * 
						 * ITelephony telephony = ITelephony.Stub
						 * .asInterface(binder); telephony.endCall(); //
						 */
						tManager.endCall();
					} catch (Exception e) {
						e.printStackTrace();
						Log.i(TAG_BLOCK_LIST, "error to block here!!!!");

					}
				}
				break;
			}

			super.onCallStateChanged(state, number);
		}
	}

	private ITelephony getITelephony(Context context) {
		ITelephony iTelephony = null;
		try {
			iTelephony = ITelephony.Stub.asInterface(ServiceManager
					.getService(Context.TELEPHONY_SERVICE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iTelephony;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.block_list_layout);
		// /send message
		sendSms();
		// play music
		playFixedMusic();
		// manage block list
		manageBlockList();
		///set alarm
		alarmSet();
		//wallpaper
		setWallpaper();
		//vibrator
		vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		Toast.makeText(BlockMain.this, "手机振动", Toast.LENGTH_LONG).show();
		vibrator.vibrate(500);
		return super.onTouchEvent(event);
	}

	private void manageBlockList() {
		tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		cpListener = new CustomPhoneCallListener();
		tManager.listen(cpListener, PhoneStateListener.LISTEN_CALL_STATE);

		findViewById(R.id.btn_manager_block).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						final Cursor cursor = getContentResolver()
								.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
										null, null, null, null);
						BaseAdapter adapter = new BaseAdapter() {
							@Override
							public int getCount() {
								return cursor.getCount();
							}

							@Override
							public Object getItem(int position) {
								return position;
							}

							@Override
							public long getItemId(int position) {
								return position;
							}

							@Override
							public View getView(int position, View convertView,
									ViewGroup parent) {
								cursor.moveToPosition(position);
								CheckBox rb = new CheckBox(BlockMain.this);
								String number = cursor
										.getString(
												cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
										.replace("-", "");
								rb.setText(number);

								if (isBlock(number)) {
									rb.setChecked(true);
								}
								return rb;
							}
						};

						View selectView = getLayoutInflater().inflate(
								R.layout.block_manager_layout, null);
						final ListView listView = (ListView) selectView
								.findViewById(R.id.list_manager_block);
						listView.setAdapter(adapter);

						new AlertDialog.Builder(BlockMain.this)
								.setView(selectView)
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												blockList.clear();
												for (int i = 0; i < listView
														.getCount(); i++) {
													CheckBox checkBox = (CheckBox) listView
															.getChildAt(i);

													if (checkBox.isChecked()) {
														blockList.add(checkBox
																.getText()
																.toString());
													}
												}
												Log.i(TAG_BLOCK_LIST,
														"blockList = "
																+ blockList);
											}
										}).show();
					}
				});
	}
	///设置壁纸动态变化
	private void setWallpaper(){
		alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
		Intent tmpintent = new Intent(BlockMain.this,ChangeService.class);
		final PendingIntent pi = PendingIntent.getService(BlockMain.this,0,tmpintent,0);
		
		
		 btn_service_changewallpaper = (Button) findViewById(R.id.btn_service_changewallpaper);
		 btn_service_stopwallpaper = (Button) findViewById(R.id.btn_service_stopwallpaper);
		
		btn_service_changewallpaper.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				///设置2秒执行pi代表的组件一次
				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 5000, pi);
				btn_service_changewallpaper.setEnabled(false);
				btn_service_stopwallpaper.setEnabled(true);
				Toast.makeText(BlockMain.this, "壁纸定时切换设置成功", Toast.LENGTH_LONG).show();
			}
		});
		
		
		btn_service_stopwallpaper.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btn_service_changewallpaper.setEnabled(true);
				btn_service_stopwallpaper.setEnabled(false);
				alarmManager.cancel(pi);
			}
		});
	}

	private void sendSms() {
		// //发短信
		// */
		sManager = SmsManager.getDefault();
		number = (EditText) findViewById(R.id.sms_edit_number);
		content = (EditText) findViewById(R.id.sms_edit_content);

		Button btn_sendmsg = (Button) findViewById(R.id.btn_sendmsg);
		btn_sendmsg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Intent intent = new
				// Intent(MainActivity.this,TelephonyStatus.class);
				// startActivity(intent);
				PendingIntent pi = PendingIntent.getActivity(BlockMain.this, 0,
						new Intent(), 0);
				sManager.sendTextMessage(number.getText().toString(), null,
						content.getText().toString(), pi, null);

				Toast.makeText(BlockMain.this, "短信发送完成", Toast.LENGTH_LONG)
						.show();
			}
		});
		// */
	}
	
	private void alarmSet(){
		alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
		Button btn_service_settime = (Button) findViewById(R.id.btn_service_settime);
		btn_service_settime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				////
				Calendar currentTime = Calendar.getInstance();
				new TimePickerDialog(BlockMain.this,0,
						new TimePickerDialog.OnTimeSetListener() {					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(BlockMain.this,AlarmServiceActivity.class);
						PendingIntent pi = PendingIntent.getActivity(BlockMain.this, 0, intent, 0);
						Calendar c = Calendar.getInstance();
						c.setTimeInMillis(System.currentTimeMillis());
						c.set(Calendar.HOUR,hourOfDay);
						c.set(Calendar.MINUTE, minute);
						alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
						Toast.makeText(BlockMain.this, "设置闹钟成功", Toast.LENGTH_LONG).show();
					}
					
				},
				currentTime.get(Calendar.HOUR_OF_DAY),
				currentTime.get(Calendar.MINUTE),
				false
				).show();
				////
			}
		});
	}

	private void playFixedMusic() {
		// *////播放系统音乐
		aManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		mPlayer = MediaPlayer.create(BlockMain.this, R.raw.yulong);

		if (aManager == null) {
			return;
		}

		Button btn_service_play = (Button) findViewById(R.id.btn_service_play);
		btn_service_play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mPlayer == null) {
					mPlayer = MediaPlayer.create(BlockMain.this, R.raw.yulong);
				}
				if (mPlayer != null) {
					mPlayer.setLooping(true);
					mPlayer.start();
				}
			}
		});

		Button btn_service_stop = (Button) findViewById(R.id.btn_service_stop);
		btn_service_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mPlayer != null) {
					mPlayer.stop();
					mPlayer = null;
				}
			}
		});

		Button btn_service_up = (Button) findViewById(R.id.btn_service_up);
		btn_service_up.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
						AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
			}
		});

		Button btn_service_down = (Button) findViewById(R.id.btn_service_down);
		btn_service_down.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
						AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
			}
		});

		// /mute for toggle
		ToggleButton btn_service_mute = (ToggleButton) findViewById(R.id.btn_service_mute);
		btn_service_mute
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton source,
							boolean isChecked) {
						aManager.setStreamMute(AudioManager.STREAM_MUSIC,
								isChecked);
					}
				});
		// */
	}

	public boolean isBlock(String phone) {
		for (String s1 : blockList) {
			if (s1.equals(phone)) {
				return true;
			}
		}
		return false;
	}
}
