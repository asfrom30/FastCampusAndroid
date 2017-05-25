package com.doyoon.android.fastcampusandroid.week2.lecture.IntentBasic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.doyoon.android.fastcampusandroid.R;

public class IntentBasicActivity extends AppCompatActivity {

    private EditText txtCall;
    private Button btnCall;

    private EditText txtWeb;
    private Button btnWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_basic);

        txtCall = (EditText) findViewById(R.id.txtCall);
        btnCall = (Button) findViewById(R.id.btnCall);

        txtWeb = (EditText) findViewById(R.id.txtWeb);
        btnWeb = (Button) findViewById(R.id.btnWeb);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = txtCall.getText().toString();

                // 안드로이드에서 자원을 가르키는 주소 체계 URI
                // Uri는 프로토콜 + 값이다. (http:// 프로토콜 + 주소
                Uri uri = Uri.parse("tel:" + phoneNumber);  // parse는 데이터를 객체화 시킨다.
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
//                Intent intent = new Intent(Intent.ACTION_CALL, uri);// ACTION_CALL은 권한이 필요하다.
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String webAddress = txtWeb.getText().toString();
                Uri uriAddress = Uri.parse("http://" + webAddress);
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, uriAddress);
                startActivity(intentWeb);
            }
        });

    }
}
