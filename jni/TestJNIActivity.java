package com.example.activitylifecycle;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
public class TestJNIActivity extends Activity {    
private TextView textView;    
static {        // 加载动态库
        System.loadLibrary("TestJNI");
    }
    @Override    
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        TestJNI testJNI = new TestJNI();        // 调用native方法
        boolean init = testJNI.Init();        
       if (init == true) {            // 调用Add函数
            int sum = testJNI.Add(100, 150);
            textView.setText("你真是个" + sum);
        } else {
            textView.setText("你比二百五还要二百五");
        }
        testJNI.Destory();
    }
}