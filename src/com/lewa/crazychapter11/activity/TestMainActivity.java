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
import android.util.Log;  
import android.util.Log;
import android.content.Context;
import android.app.Dialog;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;
import android.view.Window;
import android.graphics.Color;
import java.util.Timer;
import java.util.TimerTask;
import android.view.KeyEvent;
import android.widget.Toast;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.content.Intent;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

// import com.android.internal.app.IUsageStats;
// import com.android.internal.os.PkgUsageStats;

public class TestMainActivity extends Activity implements MyDialogListener{
    private TextView mTextViewInputContent;
    MyDialogListener listener;
    Context mContext;
    char buf[] = new char[20];
          
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);            
        mContext=this;
        Window win = getWindow();
        //set to full screen
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ///set to no title
        // requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*/
        Window window = getWindow();    
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);    
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  
        window.setStatusBarColor(R.color.background_color);
        //*/

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS  
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
            // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  
            //         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  
            //         | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);  

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  
            getWindow().setStatusBarColor(Color.TRANSPARENT);  
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

            win.requestFeature(Window.FEATURE_LEFT_ICON); 


            ////make the screen on for ever
            // getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);  


        setContentView(R.layout.activity_test_layout);

        ///must put after setContentView
        win.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.plate);

        mTextViewInputContent = (TextView) findViewById(R.id.tv_input_content);
        InitTestBtn();
        listener = (TestMainActivity)this;
        inTestData();

    }

    private void InitTestBtn(){
            Button btn_test = (Button) findViewById(R.id.btn_test);
            btn_test.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog dialog = new MyDialog(mContext,listener);
                    dialog.show();
                    dialog.setCancelable(false);
                    getPkgUsageStats();
                }
            });
        }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("algerheTest", "TestMainActivity onStart in!!");
        new Thread(){
            @Override
            public void run(){
                for (int i=0;i<100 ; i++) {
                    try{
                       Thread.sleep(2000);
                   }catch (InterruptedException e){
                    e.printStackTrace();
                }

                Log.i("algerheThreadTest", "print in new thread");
            }                
        }
    }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("algerheTest", "TestMainActivity onResume in!!");     
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("algerheTest", "TestMainActivity onPause in!!");
    }

    @Override
    protected void onStop() {
        Log.i("algerheTest", "TestMainActivity onStop in!!");

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("algerheTest", "TestMainActivity onDestroy out @@@!!");

        super.onDestroy();
    }

    @Override
     public void onOkClick(String name) {
            mTextViewInputContent.setText(name);
            Log.i("algerheTest", "onOkClick enter in !!!!");
            // mTextViewInputContent.setText(buf,0,4);
     }

     @Override
     public void onCancelClick() {
            mTextViewInputContent.setText("unknow input");
        Log.i("algerheTest", "onCancelClick enter in !!!!");
            // mTextViewInputContent.setText(buf,0,4);
     }
     
     private void inTestData(){
        String sss = "this is a demo of the getChars method.";
        char buf[] = new char[20];
        sss.getChars(10,20,buf,0);

        String str1= new String(buf,0,buf.length);
        String str2= String.valueOf(buf);
        String str3= new String(buf,0,20-10);
        
        Log.i("algerheTest1", "sss="+sss);
        Log.i("algerheTest1", "buf="+buf);
        Log.i("algerheTest1", "buf.length="+buf.length);
        Log.i("algerheTest1", "str1="+str1);
        Log.i("algerheTest1", "str1.length()="+str1.length());
        Log.i("algerheTest1", "str2="+str2);
        Log.i("algerheTest1", "str2.length()="+str2.length());
        Log.i("algerheTest1", "str3="+str3);
        Log.i("algerheTest1", "str3.length()="+str3.length());


        String str4 = System.getProperty("java.library.path");
        Log.i("algerheTest3", "str4="+str4);
     }             


     private static Boolean isExit = false;  
     private static Boolean hasTask = false;  
     Timer tExit = new Timer();  
     TimerTask task = new TimerTask() {  

        @Override  
        public void run() {  
            isExit = false;  
            hasTask = true;  

            Log.i("algerheTest2", "onKeyDown: timer expired !!");
        }  
    };  



public static void getPkgUsageStats() {  
    Log.d("algerheTAG", "[getPkgUsageStats]");  
    try {  
        Class<?> cServiceManager = Class  
                .forName("android.os.ServiceManager");  
        Method mGetService = cServiceManager.getMethod("getService",  
                java.lang.String.class);  
        Object oRemoteService = mGetService.invoke(null, "usagestats");  
  
        // IUsageStats oIUsageStats =  
        // IUsageStats.Stub.asInterface(oRemoteService)  
        Class<?> cStub = Class  
                .forName("com.android.internal.app.IUsageStats$Stub");  
        Method mUsageStatsService = cStub.getMethod("asInterface",  
                android.os.IBinder.class);  
        Object oIUsageStats = mUsageStatsService.invoke(null,  
                oRemoteService);  
  
        // PkgUsageStats[] oPkgUsageStatsArray =  
        // mUsageStatsService.getAllPkgUsageStats();  
        Class<?> cIUsageStatus = Class  
                .forName("com.android.internal.app.IUsageStats");  
        Method mGetAllPkgUsageStats = cIUsageStatus.getMethod(  
                "getAllPkgUsageStats", (Class[]) null);  
        Object[] oPkgUsageStatsArray = (Object[]) mGetAllPkgUsageStats  
                .invoke(oIUsageStats, (Object[]) null);  
        Log.d("algerheTAG", "[getPkgUsageStats] oPkgUsageStatsArray = "+oPkgUsageStatsArray);  
  
        Class<?> cPkgUsageStats = Class  
                .forName("com.android.internal.os.PkgUsageStats");  
  
        StringBuffer sb = new StringBuffer();  
        sb.append("nerver used : ");  
        for (Object pkgUsageStats : oPkgUsageStatsArray) {  
            // get pkgUsageStats.packageName, pkgUsageStats.launchCount,  
            // pkgUsageStats.usageTime  
            String packageName = (String) cPkgUsageStats.getDeclaredField(  
                    "packageName").get(pkgUsageStats);  
            int launchCount = cPkgUsageStats  
                    .getDeclaredField("launchCount").getInt(pkgUsageStats);  
            long usageTime = cPkgUsageStats.getDeclaredField("usageTime")  
                    .getLong(pkgUsageStats);  
            if (launchCount > 0)  
                Log.d("algerheTAG", "[getPkgUsageStats] "+ packageName + "  count: "  
                        + launchCount + "  time:  " + usageTime);  
            else {  
                sb.append(packageName + "; ");  
            }  
        }  
        Log.d("algerheTAG", "[getPkgUsageStats] " + sb.toString());  
    } catch (IllegalArgumentException e) {  
        e.printStackTrace();  
        Log.d("algerheTAG", "11111");
    } catch (IllegalAccessException e) {  
        Log.d("algerheTAG", "22222");
        e.printStackTrace();  
    } catch (InvocationTargetException e) {  
        Log.d("algerheTAG", "3333");
        e.printStackTrace();  
    } catch (NoSuchFieldException e) {  
        Log.d("algerheTAG", "4444");
        e.printStackTrace();  
    } catch (ClassNotFoundException e) {  
        Log.d("algerheTAG", "5555");
        e.printStackTrace();  
    } catch (NoSuchMethodException e) {  
        Log.d("algerheTAG", "6666");
        e.printStackTrace();  
    }  
}      

    /*/
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode,event);  
        Log.i("algerheTest2", "onKeyDown:keyCode="+keyCode);
        Log.i("algerheTest2", "onKeyDown:event="+event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isExit == false ) {
                isExit = true;  
                Toast.makeText(this, "再按一次后退键退出应用程序", Toast.LENGTH_SHORT).show();

                Log.i("algerheTest2", "onKeyDown:hasTask="+hasTask);
                if(!hasTask) {  
                    tExit.schedule(task, 2000);  
                }  
            } else {  
                finish();  
                System.exit(0);  
            }  
        }  
        return false;  
    } 
    //*/
}

