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

	import android.text.TextUtils;
	import android.content.ComponentName;
	import android.content.Context;
	import android.content.DialogInterface;
	import android.content.Intent;
	import android.hardware.Sensor;
	import android.hardware.SensorEvent;
	import android.hardware.SensorEventListener;
	import android.hardware.SensorManager;
	import android.os.Bundle;
	import android.os.Environment;
	import android.os.Handler;
	import android.os.Looper;
	import android.os.Message;
	import android.util.Log;
	import android.view.SubMenu;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.view.Menu;
	import android.view.MenuItem;
	import android.view.OrientationEventListener;
	import android.view.animation.AccelerateInterpolator;
	import android.widget.LinearLayout;
	import android.widget.TextView;
	import android.widget.Toast;
	import android.content.SharedPreferences;
	import android.content.res.Configuration;
	import android.widget.AdapterView;
	import android.widget.ArrayAdapter;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.ImageView;
	import android.widget.ListView;
	import android.widget.SimpleAdapter;
	import android.widget.AdapterView.OnItemClickListener;
	import android.widget.AdapterView.OnItemSelectedListener;
	import android.widget.TabHost;
	import android.telephony.CellLocation;

	import java.io.PrintStream;
	import java.lang.ref.WeakReference;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Locale;
	import java.util.Timer;
	import java.util.TimerTask;
	import java.util.Map;


	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.FilenameFilter;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.OutputStreamWriter;

	// import lewa.support.v7.app.ActionBar;
	// import lewa.support.v7.app.ActionBarActivity;
	// import lewa.support.v7.app.ActionBar.Tab;
	// import lewa.support.v7.app.ActionBar.TabListener;
	// import lewa.support.v7.app.ActionBar.LayoutParams;

	import java.lang.ref.WeakReference;

	import android.graphics.Color;
	import android.widget.BaseAdapter;
	import android.view.ViewGroup;
	import android.view.Window;
	import android.app.TabActivity;
	import android.widget.TabHost.TabSpec;
	import android.app.Activity;
	import android.app.AlertDialog;
	import android.app.NotificationManager;
	import android.app.Notification;
	import android.app.PendingIntent;

	import java.util.Calendar;

	import android.content.pm.ActivityInfo;
	import android.content.pm.PackageManager;
	import android.content.res.Configuration;
	import android.database.Cursor;
	import android.os.Looper;
	import android.provider.ContactsContract;
	import android.support.v4.content.CursorLoader;
	import android.text.InputFilter;
	import android.net.Uri;
	import android.provider.ContactsContract;
	import android.provider.ContactsContract.CommonDataKinds;
	import android.provider.ContactsContract.CommonDataKinds.Phone;
	import android.content.SharedPreferences;
	import android.content.SharedPreferences.Editor;

	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.io.BufferedReader;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.File;
	import java.io.InputStreamReader;
	import java.io.RandomAccessFile;
	import android.content.pm.PackageManager;
	import android.content.BroadcastReceiver;
	import android.content.IntentFilter;
	import android.content.pm.PackageInfo;
	import android.app.AlarmManager;
	import android.app.PendingIntent;
	import android.telephony.TelephonyManager;
	import android.os.Build;
	import android.net.wifi.WifiManager;
	import android.bluetooth.BluetoothAdapter;
	import android.provider.Settings.Secure;
	import java.lang.reflect.InvocationTargetException;
	import java.lang.reflect.Method;
	import android.telephony.SubscriptionManager;
	import android.location.LocationManager;

	//////mysql
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.*;

	////import json
	import org.json.JSONObject;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.FilenameFilter;
	import java.io.IOException;
	import org.apache.http.util.EncodingUtils;

	import java.net.URL;
	import java.net.HttpURLConnection;
	import java.io.InputStream;

	import android.view.WindowManager;
	import android.view.Window;

	import android.os.storage.StorageManager;
	import android.os.storage.StorageVolume;
	import java.lang.reflect.Method;
	import android.content.pm.IPackageDataObserver;
	import android.os.IBinder;
	import android.os.RemoteException;
	import android.app.ActivityManager;

	//import com.mysql.jdbc.Connection;
	//import com.mysql.jdbc.Statement;
// public class MainActivity extends ActionBarActivity {
	public class MainActivity extends Activity {
// public class MainActivity extends TabActivity {
		private shutdownReceiver mShoutdown;
		final int FONT_10 = 0X111;
		final int FONT_12 = 0X112;
		final int FONT_14 = 0X113;
		final int FONT_16 = 0X114;
		final int FONT_18 = 0X115;

		final int PLAIN_ITEM = 0X11b;
		final int COLOR_ITEM = 0X11c;

		final int FONT_RED = 0X116;
		final int FONT_BLUDE = 0X117;
		final int FONT_GREEN = 0X118;

		final int PICK_CONTACT = 0;

		final String FILE_NAME = "crazyit.bin";
		final String FILE_NAME_SDCARD = "/crazyitsd.bin";

		SharedPreferences preferences;
		SharedPreferences.Editor editor;

		static SharedPreferences preferencestime;
		static SharedPreferences.Editor editortime;

		public Handler handler;

		private WeakReference<Toast> mToastReference = null;
		private String[] names = new String[] { "虎头", "弄玉", "李清照", "李白" };
		private String[] descs = new String[] { "可爱的小孩", "一个擅长音乐的女孩", "一个擅长文学的女性","浪漫主义诗人" };

		private int[] imageIds = new int[] { R.drawable.tiger, R.drawable.nongyu,
			R.drawable.qingzhao, R.drawable.libai };

			static final int NOTIFICATION_ID = 0X123;
			NotificationManager nm;
			// ActionBar acionBar;

			private EditText show_txt;
			private EditText etNum;

			static final String UPPER_NUM = "upper";
			private int temp_time=0;
			CalThread calThread;

			class CalThread extends Thread {
				public Handler mHandler;

				public void run() {
					Looper.prepare();
					mHandler = new Handler() {
						@Override
						public void handleMessage(Message msg) {
							if (msg.what == 0x1234) {
								int upper = msg.getData().getInt(UPPER_NUM);
								List<Integer> nums = new ArrayList<Integer>();

								outer: for (int i = 2; i < upper; i++) {
									for (int j = 2; j <= Math.sqrt(i); j++) {
										if (i != 2 && i % j == 0) {
											continue outer;
										}
									}
									nums.add(i);
								}
								Toast.makeText(MainActivity.this, nums.toString(),
									Toast.LENGTH_LONG).show();
							}
						}
					};
					Looper.loop();

				}
			}

			@Override
			protected void onCreate(Bundle savedInstanceState) {

				//set to full screen
        		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

		        ///set to no title
		        // requestWindowFeature(Window.FEATURE_NO_TITLE);
		        // acionBar = getSupportActionBar();
		        // acionBar = getActionBar();
				// acionBar.hide();

				// Window win = getWindow();
	   //          win.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	   //          win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS  | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
	   //          win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  
	            // win.setStatusBarColor(Color.TRANSPARENT);  
	            // win.setNavigationBarColor(Color.TRANSPARENT);

				/*/
	            win.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	            win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS  | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
	            win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);  
	            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  
	            win.setStatusBarColor(Color.TRANSPARENT);  
	            win.setNavigationBarColor(Color.TRANSPARENT);
				//*/

				super.onCreate(savedInstanceState);
				setContentView(R.layout.main);
				/* setTheme(R.style.CrazyTheme); */
				AddGameBtn();
				AddNoification();
				LookupContact();
				AddServiceBtn();
				broadcastMain();
				mediaPlayerMain();
				mediaRecordSoundMain();
				cameraMain();
				recordvideoMain();
				queMySql();
				TestFragment();
				justForTest();
				LoadJson();
				AddTestBtn();
				AddUsageStatsBtn();
				AddPeopleProvideBtn();
				getInput();

			////just for test shutdown broadcast receiver
				IntentFilter mIntentFilter = new IntentFilter("android.intent.action.ACTION_SHUTDOWN");
				mIntentFilter.addAction("com.lewa.alarm.test");
				mIntentFilter.addAction("android.provider.Telephony.SECRET_CODE");
				mIntentFilter.addAction("android.intent.action.SCREEN_ON");
				mIntentFilter.addAction("android.intent.action.SCREEN_OFF");
				mShoutdown = new shutdownReceiver();
				registerReceiver(mShoutdown ,mIntentFilter);
			////test		

				preferences = getSharedPreferences("crazyit", MODE_WORLD_WRITEABLE
					| MODE_WORLD_READABLE);
				editor = preferences.edit();

				preferencestime = getSharedPreferences("RMS_Shutdown_time", MODE_WORLD_WRITEABLE|MODE_WORLD_READABLE);
				editortime = preferencestime.edit();

				SharedShutdownTimeRead();

				AddSharedPreBtn();



				etNum = (EditText) findViewById(R.id.etNum);

			// //
				int maxLength = 4;

				InputFilter[] fArray = new InputFilter[1];

				fArray[0] = new InputFilter.LengthFilter(maxLength);

				etNum.setFilters(fArray);
			// //

				calThread = new CalThread();
				calThread.start();

				Log.i("algerheMain", "MainActivity onCreate in!!");
				String page = getString(R.string.str_page,"345","24"); 
				Log.i("algerheMain", "page="+page);

			// /just for test here
				ComponentName comp = getIntent().getComponent();
				show_txt = (EditText) findViewById(R.id.show_txt);
				show_txt.setText("组件包名为：" + comp.getPackageName() + " \n 组件类名为："
					+ comp.getClassName());

				////MD5 check item
				///1.IMEI
				TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE); 
				String szImei = TelephonyMgr.getDeviceId();
				String m_szSIMSerialNm=TelephonyMgr.getSimSerialNumber(); 
				CellLocation m_location = TelephonyMgr.getCellLocation();
				String m_Line1Number = TelephonyMgr.getLine1Number();
				String m_OperatorName = TelephonyMgr.getSimOperatorName();
				Log.i("algerheTelephonyMgr", "szImei=" + szImei);
				Log.i("algerheTelephonyMgr", "m_szSIMSerialNm=" + m_szSIMSerialNm);
				Log.i("algerheTelephonyMgr", "m_location=" + m_location);
				Log.i("algerheTelephonyMgr", "m_Line1Number=" + m_Line1Number);
				Log.i("algerheTelephonyMgr", "m_OperatorName=" + m_OperatorName);

				Log.i("algerheMain01", "szImei=" + szImei);
				Log.i("algerheMain01", "m_szSIMSerialNm=" + m_szSIMSerialNm);

				///2.Pseudo-Unique ID
				String m_szDevIDShort = "35" + //we make this look like a valid IMEI 
				Build.BOARD.length()%10 + 
				Build.BRAND.length()%10 + 
				Build.CPU_ABI.length()%10 + 
				Build.DEVICE.length()%10 + 
				Build.DISPLAY.length()%10 + 
				Build.HOST.length()%10 + 
				Build.ID.length()%10 + 
				Build.MANUFACTURER.length()%10 + 
				Build.MODEL.length()%10 + 
				Build.PRODUCT.length()%10 + 
				Build.TAGS.length()%10 + 
				Build.TYPE.length()%10 + 
				Build.USER.length()%10 ; //13 digits

				Log.i("algerheMain01", "m_szDevIDShort=" + m_szDevIDShort);

				///3. Android ID
				String m_szAndroidID = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
				Log.i("algerheMain01", "m_szAndroidID=" + m_szAndroidID);

				///4.WLAN MAC Address string
				WifiManager wm = (WifiManager)getSystemService(Context.WIFI_SERVICE); 
				String m_szWLANMAC = "unknow_wifi_mac";
				if(wm !=null && wm.getConnectionInfo() != null){
					m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
				}

				Log.i("algerheMain01", "m_szWLANMAC=" + m_szWLANMAC);

				///5.BT MAC Address string
				BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter      
				m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				String m_szBTMAC = m_BluetoothAdapter.getAddress();

				Log.i("algerheMain01", "m_szBTMAC=" + m_szBTMAC);

				///6.sim serial number .getSimSerialNumber()

			// /

			///reflect test	
				checkMethod();

			// */
				final Intent alarmIntent = new Intent();
				Log.i("algerheMain00", "isLewaRom=" + isLewaRom(this,alarmIntent));


				handler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						if (msg.what == 0x4567) {
							String languageStr = null;
							String countryStr = null;

							Locale[] locList = Locale.getAvailableLocales();

							for (int i = 0; i < locList.length; i++) {
								languageStr += locList[i].getLanguage();
								countryStr += locList[i].getCountry();
							}
						// show_txt = (EditText) findViewById(R.id.show_txt);
							show_txt.setText("语言：" + languageStr + " \n 国家："
								+ countryStr);
						}else if(msg.what == 0x2789){
							Log.i("algerheAlarm","send alarm message in time="+System.currentTimeMillis()+"\n action="+alarmIntent.getAction());

							// sendBroadcast(alarmIntent);
						}
					}
				};

			 	// String strApkPath = intent.getStringExtra("apkPath");
	    //         String strCmd = "pm install -r " + strApkPath;
	    //         try {
	    //             Process install = Runtime.getRuntime().exec(strCmd);
	    //             Log.d(TAG, "install = " + install + ", strCmd =" + strCmd);
	    //         }catch (Exception ex){
	    //             Log.d(TAG, ex.getMessage());
	    //         }	
			// */
			}		

			public static void checkMethod(){
				try{
					Method function = null;	
					Class mainActivity = Class.forName("com.lewa.crazychapter11.MainActivity");
					function = mainActivity.getMethod("methodForCheck",String.class);
					String temp_str="guoguo come here";
					Log.i("algerhecheck","function ====== "+function.invoke(null,temp_str));

				}catch(NoSuchMethodException e){	
					Log.i("algerhecheck","NoSuchMethodException ====== ");	
				} catch (ClassNotFoundException e) {
					Log.i("algerhecheck","ClassNotFoundException ########### ");
				}
				catch (InvocationTargetException e) {
					e.printStackTrace();
				} 
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}

//*/   ////getExternalFilesDirs
				try{
					Class<?> ContextCompat = Class.forName("android.support.v4.content.ContextCompat");
         			Method checkSelfPermission = ContextCompat.getMethod("checkSelfPermission",Context.class,String.class);
         			Log.d("algerheReflect","checkSelfPermission="+checkSelfPermission);
				}catch (Exception e){
					Log.i("algerheReflect","NoSuchMethodException ====== 111111");	
				}


				try{
					Class<?> ContextCompat = Class.forName("android.support.v4.content.ContextCompat");
         			Method getExternalFilesDirs = ContextCompat.getMethod("getExternalFilesDirs",Context.class,String.class);
         			Log.d("algerheReflect","getExternalFilesDirs="+getExternalFilesDirs);
				}catch (Exception e){
					Log.i("algerheReflect","NoSuchMethodException ====== 222222");	
				}

				//*/			

			// TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE); 
	  //       String imei1 = tm.getDeviceId(0);
	  //       String imei2 = tm.getDeviceId(1);

	  //       String imsi1 = "";
	  //       String imsi2 = "";

			// 	try {
	  //           Class telephonyManager = Class.forName("android.telephony.TelephonyManager");
	  //           Method getSubscriberId;
	  //           if (Build.VERSION.SDK_INT == 21) {
	  //               getSubscriberId = telephonyManager.getMethod("getSubscriberId", new Class[]{long.class});
	  //               imsi1 = (String) getSubscriberId.invoke(tm, new Object[]{SubscriptionManager.getSubId(0)[0]});
	  //               imsi2 = (String) getSubscriberId.invoke(tm, new Object[]{SubscriptionManager.getSubId(1)[0]});
	  //           } else {
	  //               getSubscriberId = telephonyManager.getMethod("getSubscriberId", new Class[]{int.class});
	  //               int subId1 = (int)SubscriptionManager.getSubId(0)[0];
	  //               int subId2 = (int)SubscriptionManager.getSubId(1)[0];
	  //               imsi1 = (String) getSubscriberId.invoke(tm, new Object[]{subId1});
	  //               imsi2 = (String) getSubscriberId.invoke(tm, new Object[]{subId2});
	  //           }

	  //           Log.i("algerhecheck","imsi1="+imsi1+"\n imsi2="+imsi2);
	        // } catch (ClassNotFoundException e) {
	        //     e.printStackTrace();
	        // } catch (NoSuchMethodException e) {
	        //     e.printStackTrace();
	        // } catch (InvocationTargetException e) {
	        //     e.printStackTrace();
	        // } catch (IllegalAccessException e) {
	        //     e.printStackTrace();
	        // }
			}		

			public static void methodForCheck(String str){
				Log.i("algerhecheck","methodForCheck str="+str);	
			}

			private void LookupContact() {
				Button btnLookupContact = (Button) findViewById(R.id.btn_lookup_contact);
				btnLookupContact.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_GET_CONTENT);
						intent.setType("vnd.android.cursor.item/phone");
						startActivityForResult(intent, PICK_CONTACT);
					}
				});
				((EditText) findViewById(R.id.show)).setVisibility(View.GONE);
				((EditText) findViewById(R.id.phone)).setVisibility(View.GONE);
				btnLookupContact.setVisibility(View.GONE);
			}

			private void cal() {
				Message msg = new Message();
				msg.what = 0x1234;
				Bundle bundle = new Bundle();
				bundle.putInt(UPPER_NUM, Integer.parseInt(etNum.getText().toString()));
				msg.setData(bundle);
				calThread.mHandler.sendMessage(msg);
			}

			@Override
			public boolean onCreateOptionsMenu(Menu menu) {

				return super.onCreateOptionsMenu(menu);
			}

			@Override
			public boolean onOptionsItemSelected(MenuItem mi) {
				return true;
			}

			private void AddGameBtn() {
				Button btn_start_game = (Button) findViewById(R.id.btn_start_game);
				btn_start_game.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
					// //start game here
					// startActivity(new Intent(MainActivity.this,
					// PlaneGame.class));
					// startActivity(new Intent(MainActivity.this,
					// OtherActivity.class));
						Intent intent = new Intent(MainActivity.this,
							ExpandableListActivityTest.class);
						startActivityForResult(intent, 0);
					}
				});
			}

			private void broadcastMain() {
				Button btn_broadcast = (Button) findViewById(R.id.btn_broadcast);
				btn_broadcast.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
							com.lewa.crazychapter11.BroadcastMain.class);
						startActivity(intent);
					}
				});
			}

			private void mediaPlayerMain() {
				Button btn_mediaplayer = (Button) findViewById(R.id.btn_mediaplayer);
				btn_mediaplayer.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
							com.lewa.crazychapter11.MediaPlayerMain.class);
						startActivity(intent);
					}
				});
			}

			private void mediaRecordSoundMain() {
				Button btn_mediarecorder = (Button) findViewById(R.id.btn_mediarecorder);
				btn_mediarecorder.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
							com.lewa.crazychapter11.MediaRecordMain.class);
						startActivity(intent);
					}
				});
			}

			private void cameraMain() {
				Button btn_cameramain = (Button) findViewById(R.id.btn_cameramain);
				btn_cameramain.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
							CaptureImage.class);
						startActivity(intent);
					}
				});
			}

			private void recordvideoMain() {
				Button btn_recordvideo = (Button) findViewById(R.id.btn_recordvideo);
				btn_recordvideo.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this, RecordVideo.class);
						startActivity(intent);
					}
				});
			}

			private void mSetText(String str) {
				TextView txt = (TextView) findViewById(R.id.tv_mysql);
				txt.setText(str);
			}

			private void sqlCon() {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
	//			String url = "jdbc:mysql://192.168.142.128:3306/mysql?user=zzfeihua&password=12345&useUnicode=true&characterEncoding=UTF-8";// 链接数据库语句
				String url = "jdbc:mysql://10.0.4.170:3306/lewa?user=root&password=lewa&useUnicode=true&characterEncoding=UTF-8";// 链接数据库语句
				Connection conn = (Connection) DriverManager.getConnection(url); // 链接数据库
				Statement stmt = (Statement) conn
				.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
				String sql = "select * from user";// 查询user表语句
				ResultSet rs = stmt.executeQuery(sql);// 执行查询
				StringBuilder str = new StringBuilder();
				while (rs.next()) {
					str.append(rs.getString(1) + "\n");
				}
				mSetText(str.toString());

				rs.close();
				stmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private void queMySql() {
			Button btn_mysql = (Button) findViewById(R.id.btn_mysql);
			btn_mysql.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// //add for look up my sql here
					sqlCon();
				}
			});
		}

		private void TestFragment() {
			Button btn_fragment = (Button) findViewById(R.id.btn_fragment);
			btn_fragment.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, Fragment2MainActivity.class);
					startActivity(intent);
				}
			});
		}

		private void AddServiceBtn() {
			Button btn_start_service = (Button) findViewById(R.id.btn_start_service);
			btn_start_service.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this,
						BindServiceTest.class);
					startActivity(intent);
				}
			});

			Button btn_systemservice = (Button) findViewById(R.id.btn_systemservice);
			btn_systemservice.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this,
						TelephonyStatus.class);
					startActivity(intent);
				}
			});

			Button btn_get_blocklist = (Button) findViewById(R.id.btn_get_blocklist);
			btn_get_blocklist.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, BlockMain.class);
					startActivity(intent);
				}
			});
		}

		public void SharedShutdownTimeRead() {
			String time = preferencestime.getString("time", null);
			if (time != null) {
				Log.i("shutdown_log", "time=" + time);
			}
		}

		public static void SharedShutdownTimeWrite(String str){
			editortime.putString("time", str);
			editortime.commit();
		}

		public void SharedPreferencesRead() {
			String time = preferences.getString("time", null);
			int randNum = preferences.getInt("random", 0);
			if (time != null) {
				Log.i("crazychater8", "time=" + time);
			}
			Log.i("crazychater8", "randNum=" + randNum);
			String result = time == null ? "您暂时未输入" : "写入时间：" + time
			+ "\n 上次生成的随机数为：" + randNum;
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
		}		

		private void AddSharedPreBtn() {
			final EditText edit_in = (EditText) findViewById(R.id.edit_in);
			final EditText edit_out = (EditText) findViewById(R.id.edit_out);
			Button btn_read = (Button) findViewById(R.id.btn_read);
			btn_read.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					SharedPreferencesRead();
					// edit_out.setText(read());
					edit_out.setText(ReadFromSdCard());
				}
			});

			Button btn_write = (Button) findViewById(R.id.btn_write);
			btn_write.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"
						+ "hh:mm:ss");

					editor.putString("time", sdf.format(new Date()));
					editor.putInt("random", (int) (Math.random() * 100));
					editor.commit();

					// write(edit_in.getText().toString());
					WriteToSdCard(edit_in.getText().toString());
					edit_in.setText("");
				}
			});

			Button btn_read_sdcard = (Button) findViewById(R.id.btn_read_sdcard);
			btn_read_sdcard.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					// */
					intent.setClass(MainActivity.this, ReadSdCardActivity.class);
					// */

					startActivity(intent);
				}
			});

			Button btn_dict = (Button) findViewById(R.id.btn_dict);
			btn_dict.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					// */
					intent.setClass(MainActivity.this, DictResolvertTest.class);
					// */
					startActivity(intent);
				}
			});
		}

		private String read() {
			try {
				FileInputStream fis = openFileInput(FILE_NAME);
				byte[] buff = new byte[1024];
				int hasRead = 0;
				StringBuilder sb = new StringBuilder("");

				while ((hasRead = fis.read(buff)) > 0) {
					sb.append(new String(buff, 0, hasRead));
				}
				fis.close();
				return sb.toString();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		private void write(String content) {
			try {
				/* 1/MODE_APPEND 在后面接上 2／MODE_PRIVATE 覆盖清空 */
				FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);// MODE_APPEND
				PrintStream ps = new PrintStream(fos);
				ps.println(content);
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// */
		private String ReadFromSdCard() {
			try {
				if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
					File sdCardDir = Environment.getExternalStorageDirectory();
				FileInputStream fis = new FileInputStream(
					sdCardDir.getCanonicalPath() + FILE_NAME_SDCARD);

				Log.i("crazyFile", "ReadFromSdCard:sdCardDir=" + sdCardDir
					+ " \n fis=" + fis);

				BufferedReader br = new BufferedReader(new InputStreamReader(
					fis));
				StringBuilder sb = new StringBuilder("");
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				Log.i("crazyFile",
					"ReadFromSdCard:sb.toString()=" + sb.toString());
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("crazyFile", "ReadFromSdCard: read error here !!!");
		}

		return null;
	}

	private void WriteToSdCard(String content) {
		try {
			if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
				File sdCardDir = Environment.getExternalStorageDirectory();
			File targetFile = new File(sdCardDir.getCanonicalPath()
				+ FILE_NAME_SDCARD);

			Log.i("crazyFile", "WriteToSdCard: sdCardDir=" + sdCardDir
				+ " \n targetFile=" + targetFile);
			RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
			raf.seek(targetFile.length());
			raf.write(content.getBytes());
			raf.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

		// */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		switch (requestCode) {
			case PICK_CONTACT:
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = intent.getData();
				CursorLoader cursorLoader = new CursorLoader(this, contactData,
					null, null, null, null);

				Cursor cursor = cursorLoader.loadInBackground();
				if (cursor != null && cursor.moveToFirst()) {
					Log.i("algerheContact", " comhere 111");
					String contactId = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Contacts._ID));
					String name = cursor
					.getString(cursor
						.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
					String phoneNumber = "此联系人暂未输入电话号码";

					Cursor phones = getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
						+ "=" + contactId, null, null);
					if (phones == null) {
						return;
					}
						// Log.i("algerheContact"," comhere 222 phones.moveToFirst()="+phones.moveToFirst());
					if (phones.moveToFirst()) {
						phoneNumber = phones
						.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					}

					phones.close();
					EditText show = (EditText) findViewById(R.id.show);

					show.setText(name);
					EditText phone = (EditText) findViewById(R.id.phone);
					phone.setText(phoneNumber);

					show.setVisibility(View.GONE);
					phone.setVisibility(View.GONE);
				}
				cursor.close();
			}
			break;
		}

		if (requestCode == 0 && resultCode == 0 && intent != null) {
			Bundle data = intent.getExtras();
			if (data == null) {
				return;
			}
			String resultArms = data.getString("armType");
			String showArms = "您选择的兵种是：" + resultArms;
			show_txt = (EditText) findViewById(R.id.show_txt);
			if (resultArms != null && show_txt != null) {
				show_txt.setText(showArms);
			}
		}
	}

	private void ChangeOrientation() {
		Configuration cfg = getResources().getConfiguration();
		if (cfg.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			MainActivity.this
			.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}

		if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT) {
			MainActivity.this
			.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}

	private void AddNoification() {
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Button btn_send = (Button) findViewById(R.id.btn_send);
		Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
			/*
			 * btn_send.setOnClickListener(new OnClickListener() {
			 * 
			 * @Override public void onClick(View source) { // acionBar.show();
			 * EditText show_txt=(EditText)findViewById(R.id.show_txt);
			 * show_txt.setText("yong onClick chufa de"); } });
			 */
			btn_send.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View source) {

					Intent intent = new Intent();
					// acionBar.hide();
					/*
					 * Intent intent = new Intent(MainActivity.this,
					 * MainActivity.class); startActivity(intent);
					 */

					/*
					 * Toast.makeText(this, "系统屏幕方向变化" + "\n 修改后的方向是：" + screen,
					 * Toast.LENGTH_LONG).show();
					 */

					/*
					 * Message msg = new Message(); msg.what = 0x4567;
					 * handler.sendMessage(msg);
					 */

					// */
					// intent.setClass(MainActivity.this, GestureFlip.class);
					// */

					intent.setClass(MainActivity.this, HTMLActivity.class);				
					// intent.setClass(MainActivity.this, HTMLMainActivity.class);
					startActivity(intent);				
				}
			});

			btn_cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View source) {
					// acionBar.hide();
					Intent intent = new Intent();
					// intent.setAction("android.intent.action.OTHERACTIVITY");
					// intent.addCategory("android.intent.category.OTHERACTIVITY");
					// intent.setAction(Intent.ACTION_MAIN);
					// intent.addCategory(Intent.CATEGORY_HOME);
					// intent.setDataAndType(Uri.parse("lee://www.fkjava.org:8888/mypath"),"abc/xyz");

					// /ACTION_VIEW
					/*
					 * / String data = "http://www.baidu.com"; Uri uri =
					 * Uri.parse(data); intent.setAction(Intent.ACTION_VIEW);
					 * intent.setData(uri); /
					 */

					// /ACTION_EDIT
					/*
					 * / intent.setAction(Intent.ACTION_EDIT);
					 * intent.setData(Uri.parse
					 * ("content://com.android.contacts/contacts/1")); //
					 */

					// /ACTION_EDIT
					intent.setAction(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:10086")); 

					// /ACTION_res
					/*
					 * / intent.setClass(MainActivity.this,ValuesResTest.class); //
					 */

					// /ACTION_res
					/*
					 * / intent.setClass(MainActivity.this, ClipDrawableTest.class);
					 * //
					 */

					/*
					 * / intent.setClass(MainActivity.this, LewaBitmapTest.class);
					 * //
					 */

					/*
					 * / intent.setClass(MainActivity.this, LewaDrawView.class); //
					 */

					/*
					 * / intent.setClass(MainActivity.this, HandDraw.class); //
					 */

					/*
					 * / intent.setClass(MainActivity.this, MoveBack.class); //
					 */

					/*
					 * / intent.setClass(MainActivity.this, SurfaceViewTest.class);
					 * //
					 */

					/*
					 * / intent.setClass(MainActivity.this, DBTest.class); //
					 */

					/*
					 * / intent.setClass(MainActivity.this, Dict.class); //
					 */

					/*
					 * / intent.setClass(MainActivity.this, GestureZoom.class); //
					 */

					// */
					// intent.setClass(MainActivity.this, GestureFlip.class);
					// */
					// intent.addCategory("android.intent.category.APP_CALCULATOR");
					// PackageManager packageManager = MainActivity.this.getPackageManager(); 
					// try { 
					// 	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// 	intent =packageManager.getLaunchIntentForPackage("com.android.calculator2"); 
					// 	startActivity(intent);
					// } catch (Exception e) { 
					// 	Log.i("algerheMain", e.toString()); 
					// }

					// Intent i = new Intent(Intent.ACTION_MAIN);
					// i.setComponent(new ComponentName("com.android.calculator2",
					// 	"com.android.calculator2.Calculator"));
					// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
					// startActivity(i);
				}
			});
	}

	private void showTost(String content, int tostLength) {
		Toast mToast;
		if (mToastReference != null) {
			mToast = mToastReference.get();
			if (mToast != null) {
				mToast.cancel();
				mToastReference = null;
			}
		}
		mToast = Toast.makeText(MainActivity.this, content, tostLength);
		mToastReference = new WeakReference<Toast>(mToast);
		mToast.show();
	}

	public void clickHandler(View source) {
			// cal();
		Bundle data = new Bundle();
		data.putString("name", "algerhe");
			// Intent intent = new Intent(MainActivity.this, OtherActivity.class);
		Intent intent = new Intent(MainActivity.this, SelectBookActivity.class);
		intent.putExtras(data);
		startActivity(intent);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕"
		: "竖向屏幕";

		Toast.makeText(this, "系统屏幕方向变化" + "\n 修改后的方向是：" + screen,
			Toast.LENGTH_LONG).show();
		show_txt = (EditText) findViewById(R.id.show_txt);
		show_txt.setText("方向改变了");

		Log.v("algerhe2", "onConfiguration changed entry !!!");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("algerheMainActivity", "MainActivity onStart in!!");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("algerheMainActivity", "MainActivity onResume in!!");		
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("algerheMainActivity", "MainActivity onPause in!!");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("algerheMainActivity", "MainActivity onStop in!!");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("algerheMainActivity", "MainActivity onDestroy in!!");
	}

		// /检测线程
	public boolean isLewaRom(Context context,Intent alarmIntent) {
		PackageManager pm = context.getPackageManager();
		boolean isLewaRom = true;
		String version="";
		int versionCode=0;
		PackageInfo pi=null;

		String testsr=null;
		try {
				// com.lewa.permmanager
				// pm.getPackageInfo("com.lewa.deviceactivate",PackageManager.GET_ACTIVITIES);
			pm.getPackageInfo("com.lewa.permmanager",
				PackageManager.GET_ACTIVITIES);

			pi = pm.getPackageInfo(context.getPackageName(), 0);
			version = pi.versionName;
			versionCode= pi.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			isLewaRom = false;
		}
		Log.d("algerheMain00", "isLewaRom : " + isLewaRom);
		Log.i("algerheVersion","versionname="+version+" \n getPackageName()="+getPackageName()+" \n versioncode="+versionCode);
		Log.i("algerheStr","TextUtils.isEmpty(testsr) = "+TextUtils.isEmpty(testsr));
			// Log.i("algerheStr","testsr.length="+testsr.length());

			///alarm test
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		alarmIntent.setAction("com.lewa.alarm.test");
		PendingIntent pipi = PendingIntent.getBroadcast(context, 3359, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Log.i("algerheAlarm","send alarm message in time="+System.currentTimeMillis());

		// alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000, pipi);

		alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pipi);

		// final Timer timer = new Timer();

		// timer.schedule(new TimerTask() {
		// 	@Override
		// 	public void run() {
		// 		Message msg = new Message();
		// 		msg.what = 0x2789;	

		// 		handler.sendMessage(msg);

		// 		timer.cancel();
		// 	}
		// }, 0, 5000);

		return isLewaRom;
	}

	public boolean hasGPSDevice(Context context){
		final LocationManager mgr = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		if ( mgr == null )
			return false;
		final List<String> providers = mgr.getAllProviders();
		if ( providers == null ) 
			return false;

		Log.i("algerheGps","System hasGps="+providers.contains(LocationManager.GPS_PROVIDER));
		return providers.contains(LocationManager.GPS_PROVIDER);
	}

	private void justForTest() {
		int i = 2;
		int j;
		Integer wrapperri = Integer.valueOf(i);
		i = 7;
		j = wrapperri.intValue();

		Log.i("algerhe_test_int", "i=" + i + "  j=" + j + "  wrapperri="+ wrapperri);

		String testString=com.lewa.mytestservice.MyService.commononString();
		// Log.i("algerheTest","justForTest:testString="+testString);

		Log.i("algerheTest","filepath: Environment.getExternalStorageDirectory()="+Environment.getExternalStorageDirectory());
		Log.i("algerheTest","filepath: getExternalCacheDir()="+getExternalCacheDir(this));

		writeFile("My name is Algerhe"); 
		writeFileToSD("Wo De MingZi Shi Hechunhua zai sd card shang !");
		Log.i("algerheTest","filepath: getSDPath()="+getSDPath());
		// Log.i("algerheTest","filepath: getStoragePath(this,true)="+getStoragePath(this,true));
		// Log.i("algerheTest","filepath: getStoragePath(this,false)="+getStoragePath(this,false));

		StorageManager storageManager = (StorageManager) this.getSystemService(Context.STORAGE_SERVICE);
        String[] sdcardlist = storageManager.getVolumePaths();

        String mSDCardPath = null;
        String mSDCard2Path = null;
        mSDCardPath = sdcardlist[0];
        mSDCard2Path = sdcardlist[1];
        Log.i("algerheTest","filepath: mSDCardPath="+mSDCardPath);
        Log.i("algerheTest","filepath: mSDCard2Path="+mSDCard2Path);
        Log.i("algerheTest","filepath: Environment.isExternalStorageRemovable()="+Environment.isExternalStorageRemovable());

        Log.i("algerheTest","filepath: isExistSDCard()="+isExistSDCard());

        String TRACKFILEROOT = this.getExternalFilesDir("Android").getAbsolutePath();
        Log.i("algerheTest","filepath: TRACKFILEROOT="+TRACKFILEROOT);
	}

	private static final String JSON_KEY_ALGERHE = "algerhe";
	private static final String CONFIG_FILE_NAME = "jscrazy.json";
    private static final String DEFAULT_CONFIG_FILE_PATH = "/system/etc/res/" + CONFIG_FILE_NAME;
	private String mAlgerhe="unknow";
	private String mAlgeryang="unknow";


    private static String loadConfig(String filePath) {
        File file = new File(filePath);
        Log.i("algerheJson","file="+file);
        if (!file.exists()) {
        	Log.i("algerheJson","not exist json file !~~~~");
            return "";
        }
        byte [] datas = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int avalibleLength = fis.available();
            datas = new byte[avalibleLength];
            fis.read(datas);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != datas) {
        	Log.i("algerheJson","datas="+datas);

            return EncodingUtils.getString(datas, "UTF-8");
        }
        return "";
    }
	private void LoadJson(){
		String jsonFile = loadConfig(DEFAULT_CONFIG_FILE_PATH);
		Log.i("algerheJson","jsonFile="+jsonFile);
		try{
		JSONObject jsobj = new JSONObject(jsonFile);
            if (jsobj.has(JSON_KEY_ALGERHE)) {
                mAlgerhe = jsobj.getString(JSON_KEY_ALGERHE);
            }
            if (jsobj.has("yanger")) {
                mAlgeryang = jsobj.getString("yanger");
            }
        }catch(Exception e){
        	Log.i("algerheJson","LoadJson:catch error ! exception");
        }

            Log.i("algerheJson","LoadJson:mAlgerhe="+mAlgerhe +"\nmAlgeryang="+mAlgeryang);
	}

	////clear destk
    private void refreshDesk()  
				{  
					      ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);  
					   
					try{  
					Log.i("algerheTest","refreshDesk()----->clear user data");  
					Method clearUserdata = am.getClass().getDeclaredMethod("clearApplicationUserData",  
					String.class,IPackageDataObserver.class);  
					clearUserdata.setAccessible(true);  
					clearUserdata.invoke(am, "com.android.launcher", new PackageDataClearObserver());  
					}catch (Exception e) {  
					//TODO Auto-generated catch block  
					e.printStackTrace();  
					}  
					}  
					   
	class PackageDataClearObserver implements IPackageDataObserver{  
					   
					@Override  
					public IBinder asBinder() {  
					//TODO Auto-generated method stub  
					return null;  
					}  
					   
					@Override  
					public void onRemoveCompleted(String arg0, boolean arg1)  
					throws RemoteException {  
					//TODO Auto-generated method stub  
					}  
					}  
					////end clear


	private void AddTestBtn(){
			Button btn_test = (Button) findViewById(R.id.btn_test);
			btn_test.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					// */
					intent.setClass(MainActivity.this, TestMainActivity.class);
					// */
					startActivity(intent);

					refreshDesk();
				}
			});
		}
    
    private void AddUsageStatsBtn(){
			Button btn_usagestats = (Button) findViewById(R.id.btn_usagestats);
			btn_usagestats.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					// */
					intent.setClass(MainActivity.this, UsageStatsActivity.class);
					// */
					startActivity(intent);
				}
			});
		}		

	private void AddPeopleProvideBtn(){
			Button btn_people_provider = (Button) findViewById(R.id.btn_people_provider);
			btn_people_provider.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, ProviderMainActivity.class);
					startActivity(intent);
				}
			});
		}	

	private void getInput(){      
        try  
        {  
        URL url = new URL("http://www.google.cn/");  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        conn.setDoInput(true);  
        conn.setConnectTimeout(10000);  
        conn.setRequestMethod("GET");  
        conn.setRequestProperty("accept", "*/*");  
        String location = conn.getRequestProperty("location");  
        Log.i("algerheHttp","location="+location);
        int resCode = conn.getResponseCode();  
        conn.connect();  
        InputStream stream = conn.getInputStream();  
        byte[]  data=new byte[102400];  
        int length=stream.read(data);  
        String str=new String(data,0,length);         
        conn.disconnect();  
        // System.out.println(str);
        Log.i("algerheHttp","str="+str);
        stream.close();  
        }  
        catch(Exception ee)  
        {  
            System.out.print("ee:"+ee.getMessage());              
        }  
    }

    public String getSDPath(){ 
       File sdDir = null; 
       boolean sdCardExist = Environment.getExternalStorageState()   
       .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
       if(sdCardExist)   
       {                               
         sdDir = Environment.getExternalStorageDirectory();//获取跟目录
       }   
       return sdDir.toString(); 
	}

    /*/
	private  String getStoragePath(Context mContext, boolean is_removale) {    
  
     	StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);  
        Class<?> storageVolumeClazz = null;  
        try {  
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");  
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");  
            Method getPath = storageVolumeClazz.getMethod("getPath");  
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");  
            Object result = getVolumeList.invoke(mStorageManager);  
            final int length = Array.getLength(result);  
            for (int i = 0; i < length; i++) {  
                Object storageVolumeElement = Array.get(result, i);  
                String path = (String) getPath.invoke(storageVolumeElement);  
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);  
                if (is_removale == removable) {  
                    return path;  
                }  
            }  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            e.printStackTrace();  
        } catch (NoSuchMethodException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
        return null;  
	} 
	//*/ 

    private File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(new File(dataDir, context.getPackageName()), "files"),"testfiles");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                Log.w("error", "Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                Log.i("error", "Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }

    private File getSDCardCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getSecondaryStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(new File(dataDir, context.getPackageName()), "files"),"testfiles");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                Log.w("error", "Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                Log.i("error", "Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }


    private boolean isExistSDCard() {  
    	if (android.os.Environment.getExternalStorageState().equals(  
    		android.os.Environment.MEDIA_MOUNTED)) {  
    		return true;  
    } else  
    return false;  
	}

    public synchronized void writeFile(String text) {
    	
        File file = new File(getExternalCacheDir(this) + "test1.txt");
        BufferedWriter br = null;
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            br.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public synchronized void writeFileToSD(String text) {
    	
        File file = new File(getSDCardCacheDir(this) + "test2.txt");
        BufferedWriter br = null;
        File dir = file.getParentFile();
        if (dir !=null && !dir.exists()) {
            dir.mkdirs();
        }

        try {
            if (file !=null && !file.exists()) {
                file.createNewFile();
            }
            br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            br.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
