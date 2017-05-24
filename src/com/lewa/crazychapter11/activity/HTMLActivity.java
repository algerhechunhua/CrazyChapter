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
import android.os.Bundle;  
import android.util.Log;  
import android.view.View;  
import android.webkit.ConsoleMessage;  
import android.webkit.WebChromeClient;  
import android.webkit.WebResourceResponse;  
import android.webkit.WebSettings;  
import android.webkit.WebView;  
import android.webkit.WebViewClient;  
import android.widget.FrameLayout;

public class HTMLActivity extends Activity {  
      
    private FrameLayout frameLayout = null;  
    private WebView webView = null;  
    private WebChromeClient chromeClient = null;  
    private View myView = null;  
    private WebChromeClient.CustomViewCallback myCallBack = null;  
      
      
    public HTMLActivity() {  
    }  
      
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  

        //*/  
        super.onCreate(savedInstanceState);  
          
        setContentView(R.layout.activity_main);  
          
        frameLayout = (FrameLayout)findViewById(R.id.framelayout);  
        webView = (WebView)findViewById(R.id.webview);  
          
        webView.getSettings().setJavaScriptEnabled(true);  
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);  
          
          
        webView.setWebViewClient(new MyWebviewCient());  
          
        chromeClient = new MyChromeClient();  
          
        webView.setWebChromeClient(chromeClient);  
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);  
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);  
  
        webView.setHorizontalScrollBarEnabled(false);  
        webView.setVerticalScrollBarEnabled(false);   
          
        final String USER_AGENT_STRING = webView.getSettings().getUserAgentString() + " Rong/2.0";  
        webView.getSettings().setUserAgentString( USER_AGENT_STRING );  
        webView.getSettings().setSupportZoom(false);  
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);  
        webView.getSettings().setLoadWithOverviewMode(true);  
          
        // webView.loadUrl("file:///android_asset/intext_canvas.html");  
        webView.loadUrl("file:///android_asset/intext_eg_planets.html");
          
        if(savedInstanceState != null){  
            webView.restoreState(savedInstanceState);  
        }  

        //*/
          
    }  
      
    @Override  
    public void onBackPressed() { 
    Log.i("algerheHTML","myView="+myView) ;
        if(myView == null){  
            super.onBackPressed();
            webView.destroy();  
        }  
        else{  
            chromeClient.onHideCustomView();  
        }  
    }  
      
    @Override  
    protected void onSaveInstanceState(Bundle outState) {  
        // TODO Auto-generated method stub  
        webView.saveState(outState);  
    }  
      
    public void addJavaScriptMap(Object obj, String objName){  
        webView.addJavascriptInterface(obj, objName);  
    }  
      
    public class MyWebviewCient extends WebViewClient{  
        @Override  
        public WebResourceResponse shouldInterceptRequest(WebView view,  
                String url) {  
            WebResourceResponse response = null;  
            response = super.shouldInterceptRequest(view, url);  
            return response;  
        }  
  
        @Override  
        public void onPageFinished(WebView view, String url) {  
            // TODO Auto-generated method stub  
            Log.i("algerheHTML","***on page finished") ;
            super.onPageFinished(view, url);  
            Log.d("dream", "***on page finished");  
            // webView.loadUrl("javascript:myFunction()");   
        }  
          
    }  
      
    public class MyChromeClient extends WebChromeClient{  
          
        @Override  
        public void onShowCustomView(View view, CustomViewCallback callback) {  
            if(myView != null){  
                callback.onCustomViewHidden();  
                return;  
            }  
            frameLayout.removeView(webView);  
            frameLayout.addView(view);  
            myView = view;  
            myCallBack = callback;  
        }  
          
        @Override  
        public void onHideCustomView() { 
        Log.i("algerheHTML","onHideCustomView :myView="+myView) ; 
            if(myView == null){  
                return;  
            } 

            frameLayout.removeView(myView);  
            myView = null;  
            frameLayout.addView(webView);  
            myCallBack.onCustomViewHidden();  
        }  
          
        @Override  
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {  
            // TODO Auto-generated method stub  
            Log.d("ZR", consoleMessage.message()+" at "+consoleMessage.sourceId()+":"+consoleMessage.lineNumber());  
            return super.onConsoleMessage(consoleMessage);  
        }  
    }  
      
}

