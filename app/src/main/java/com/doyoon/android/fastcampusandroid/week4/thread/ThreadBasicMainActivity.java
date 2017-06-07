package com.doyoon.android.fastcampusandroid.week4.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.fastcampusandroid.R;

public class ThreadBasicMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_basic_main);

        Button basicBtn = (Button) findViewById(R.id.threadBasic_btn_basic);
        Button testBtn = (Button) findViewById(R.id.threadBasic_btn_test);
        Button rainBtn = (Button) findViewById(R.id.threadBasic_btn_rain);

        basicBtn.setOnClickListener(this);
        testBtn.setOnClickListener(this);
        rainBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.threadBasic_btn_basic:
                intent = new Intent(this, ThreadBasicSubActivity.class);
                startActivity(intent);
                break;
            case R.id.threadBasic_btn_test:
                intent = new Intent(this, ThreadBasicTestActivity.class);
                startActivity(intent);
                break;
            case R.id.threadBasic_btn_rain:
                intent = new Intent(this, ThreadBasicRainActivity.class);
                startActivity(intent);
        }
    }
}


