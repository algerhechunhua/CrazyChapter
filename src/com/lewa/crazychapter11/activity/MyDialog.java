package com.lewa.crazychapter11;

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

class MyDialog extends Dialog implements OnClickListener {
    private Button       okButton;
    private Button       cancelButton;
    private EditText     nameEditText;
    MyDialogListener listener;
    Context mContet;

    public MyDialog(Context context){
        super(context);
        mContet = context;
    }

    public MyDialog(Context context, MyDialogListener listener) {
        super(context);
        this.listener = listener;
        mContet = context;
    }
   
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TestApp", "Dialog created");
        setContentView(R.layout.mydialog);

         okButton = (Button) findViewById(R.id.okButton);
         cancelButton = (Button) findViewById(R.id.cancelButton);
         nameEditText = (EditText) findViewById(R.id.nameEditText);
         okButton.setOnClickListener(this);
         cancelButton.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
             case R.id.okButton:
                  listener.onOkClick(nameEditText.getText().toString());                         
                  dismiss();
                  break;
            case R.id.cancelButton:
                  cancel();
                  break;
        }
    }
}