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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.WindowManager;
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
import android.view.KeyEvent;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import lewa.support.v7.app.ActionBar;
// import lewa.support.v7.app.ActionBarActivity;
// import lewa.support.v7.app.ActionBar.Tab;
// import lewa.support.v7.app.ActionBar.TabListener;
// import lewa.support.v7.app.ActionBar.LayoutParams;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.Window;
import android.view.Display;
import android.app.Activity;
import android.util.Log;


public class PlaneGame extends Activity {	
	final private int speed = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		final PlaneView planeView = new PlaneView(this);
		setContentView(planeView);
		planeView.setBackgroundResource(R.drawable.background);
		
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		
		display.getMetrics(metrics);
		
		planeView.currentX = metrics.widthPixels / 2;
		planeView.currentY = metrics.heightPixels/2-40;
		
		Log.i("algerhe","0000 oncreate planeView.currentY="+planeView.currentY);
		
//		planeView.setOnKeyListener(new OnKeyListener(){
//			@Override
//			public boolean onKey(View source,int keyCode,KeyEvent event){
//				Log.i("algerhe","111 oncreate event.getKeyCode()="+event.getKeyCode());
//				switch (event.getKeyCode()){
//				case KeyEvent.KEYCODE_VOLUME_UP:
//					Log.i("algerhe","in volume up and planeView.currentY="+planeView.currentY);
//					planeView.currentY += speed;
//					break;
//				case KeyEvent.KEYCODE_VOLUME_DOWN:
//					Log.i("algerhe","in volume down planeView.currentY"+planeView.currentY);
//					planeView.currentY -=speed;
//					break;
//				}
//				
//				planeView.invalidate();
//				return true;
//			}
//		});
		
		planeView.setOnTouchListener(new OnTouchListener(){
			/*@Override
			public boolean onTouch(View view,MotionEvent event){								
				planeView.currentX=event.getX();
				planeView.currentY=event.getY();
				planeView.invalidate();
				return true;
			}*/
			
			@Override
			public boolean onTouch(View view,MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					Log.v("algerhe", "up:planeView.currentX="+event.getX()+"  planeView.currentY="+event.getY());
					break;
				case MotionEvent.ACTION_DOWN:
					Log.v("algerhe", "down:planeView.currentX="+event.getX()+"  planeView.currentY="+event.getY());
					planeView.currentX=event.getX();
					planeView.currentY=event.getY();
					planeView.invalidate();
					 break;
				case MotionEvent.ACTION_CANCEL:
					Log.v("algerhe", "cancel:planeView.currentX="+event.getX()+"  planeView.currentY="+event.getY());
					break;
				}
				return false;
			}
		});
		
		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		
		
		Log.v("algerhe2", "plane game xxxx onConfiguration changed entry !!!");
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}

