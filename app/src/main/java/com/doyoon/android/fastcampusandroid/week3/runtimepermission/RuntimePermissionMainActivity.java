package com.doyoon.android.fastcampusandroid.week3.runtimepermission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.fastcampusandroid.R;

// 권한 획득하기 전 권한 유효성 체크 > 설명이 필요할 경우 처리 > 권한 획득을 위한 api > 결과처리
public class RuntimePermissionMainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission_main2);

//        /* 또이러네... 무조건 final을 붙어야 되네... */
//        final ContactActivity contactActivity = new ContactActivity();
//
//        ((Button) findViewById(R.id.btn_start_contact)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                contactActivity.getContactList();
//            }
//        });

        ((Button) findViewById(R.id.btn_start_contact)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CheckPermissionActivity.class);
                startActivity(intent);
            }
        });

    }
}
