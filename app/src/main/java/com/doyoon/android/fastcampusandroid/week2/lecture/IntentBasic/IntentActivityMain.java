package com.doyoon.android.fastcampusandroid.week2.lecture.IntentBasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.fastcampusandroid.MainActivity;
import com.doyoon.android.fastcampusandroid.R;

public class IntentActivityMain extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "================================onCreate");
        setContentView(R.layout.activity_intent_main);

        // 1. 액티비티를 두개 생성합니다.
        // 가. GeneralActivity, TransparentActivity
        // 2. 현재 액티비티에 버튼 두개를 생성한 후에
        // 위에서 만든 각 Activit를 호출 하세요

        ((Button) findViewById(R.id.btnGeneral)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivityMain.this, GeneralActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btnTransparent)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivityMain.this, TransparentActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "================================onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "================================onResume");
    }

    // Activity Running

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "================================onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "================================onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "================================onRestart");
    }
}
