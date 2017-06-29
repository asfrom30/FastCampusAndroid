package com.doyoon.android.fastcampusandroid.week6.uselibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.doyoon.android.fastcampusandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity {

    @BindView(R.id.client_ev_title)
    EditText editTitle;
    @BindView(R.id.client_ev_content)
    EditText editContent;
    @BindView(R.id.client_btn_send)
    Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // 버터 나이프 활성화..
        // 함수 바인딩도 되고.... Array도 바인딩이 된다.
        ButterKnife.bind(this);
    }
}
