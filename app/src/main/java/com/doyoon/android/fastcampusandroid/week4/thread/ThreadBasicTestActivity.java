package com.doyoon.android.fastcampusandroid.week4.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.doyoon.android.fastcampusandroid.R;

public class ThreadBasicTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thead_basic_test);

        // 1. 버튼을 클릭하면 1부터 10만까지 출력하는 함수를 실행
        findViewById(R.id.threadBasic_btn_run_10T).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print100T("MainThread ");
            }
        });

        // 2 Thread 클래스에서 1부터 10만까지 출력하는 함수를 실행
        new Thread() {
            @Override
            public void run() {
                print100T("SubThread ");
            }
        }.start();
    }

    public void print100T(String caller) {
        for(int i=0; i<100; i++) {
//            System.out.println(caller + " : Current Number=====" + i);
            Log.i("100T", caller + " : Current Number=====" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
