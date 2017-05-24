package com.lewa.crazychapter11;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProviderMainActivity extends Activity {

    private EditText nameText;
    private EditText ageText;
    private EditText heightText;
    private EditText idEntry;
    private TextView labelView;
    private TextView displayView;
    private Button add;
    private Button queryAll;
    // private Button clear;
    // private Button del;
    // private Button query;
    private Button deleteAll;
    private Button update;
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_main_activity_layout);
        resolver = this.getContentResolver();
        initView();
        initEvent();
    }

    private void initView() {

        nameText = (EditText) findViewById(R.id.et_name);
        ageText = (EditText) findViewById(R.id.et_age);
        heightText = (EditText) findViewById(R.id.et_height);
        idEntry = (EditText) findViewById(R.id.et_id);

         add = (Button) findViewById(R.id.btn_add);
         queryAll = (Button) findViewById(R.id.btn_queryAll);
         // clear = (Button) findViewById(R.id.btn_clear);
         // del = (Button) findViewById(R.id.btn_del);
         // query = (Button) findViewById(R.id.btn_query);
         deleteAll = (Button) findViewById(R.id.btn_deleteAll);
         update = (Button) findViewById(R.id.btn_update);
         
         displayView = (TextView) findViewById(R.id.tv_displayView);
         labelView = (TextView) findViewById(R.id.tv_labelView);
    }

    private void initEvent() {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(People.KEY_NAME, nameText.getText().toString());
                values.put(People.KEY_AGE, Integer.parseInt(ageText.getText().toString()));
                values.put(People.KEY_HEIGHT, Float.parseFloat(heightText.getText().toString()));
                Uri newUri = resolver.insert(People.CONTENT_URI, values);
                labelView.setText("添加成功，URI:" + newUri);
            }
        });

        queryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = resolver.query(People.CONTENT_URI,
                        new String[]{People.KEY_ID, People.KEY_NAME, People.KEY_AGE, People.KEY_HEIGHT},
                        null, null, null);
                if (cursor == null) {
                    labelView.setText("数据库中没有数据");
                    return;
                }
                labelView.setText("数据库：" + String.valueOf(cursor.getCount()) + "条记录");
                String msg= "";
                if (cursor.moveToFirst()) {
                    do {
                        msg += "ID: " + cursor.getString(cursor.getColumnIndex(People.KEY_ID)) + ",";
                        msg += "姓名: " + cursor.getString(cursor.getColumnIndex(People.KEY_NAME)) + ",";
                        msg += "年龄: " + cursor.getInt(cursor.getColumnIndex(People.KEY_AGE)) + ",";
                        msg += "身高: " + cursor.getFloat(cursor.getColumnIndex(People.KEY_HEIGHT)) + ",";
                        msg += "\n";
                    } while (cursor.moveToNext());
                }
                displayView.setText(msg);
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolver.delete(People.CONTENT_URI, null, null);
                String msg = "数据全部删除";
                labelView.setText(msg);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(People.KEY_NAME, nameText.getText().toString());
                values.put(People.KEY_AGE, Integer.parseInt(ageText.getText().toString()));
                values.put(People.KEY_HEIGHT, Float.parseFloat(heightText.getText().toString()));
                Uri uri = Uri.parse(People.CONTENT_URI_STRING + "/" + idEntry.getText().toString());
                int result = resolver.update(uri, values, null, null);
                String msg = "更新ID为" + idEntry.getText().toString() + "的数据" + (result > 0 ? "成功" : "失败");
                labelView.setText(msg);
            }
        });
    }
    
}