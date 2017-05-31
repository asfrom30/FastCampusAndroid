package com.doyoon.android.fastcampusandroid.week3.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

public class ActivityControlSub extends AppCompatActivity {

    private EditText inputText;
    private TextView outputText;
    private Button returnBtn;
    private String myValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_sub);

        outputText = (TextView) findViewById(R.id.control_Sub_tv_result);
        inputText = (EditText) findViewById(R.id.controlSub_editText_input);
        /* 왜 변수명이 있으면 안되지? */
//        returnBtn = ((Button) findViewById(R.id.controlSub_btn_return)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int num1 = Integer.parseInt(myValue);
//                int num2 = Integer.parseInt(inputText.getText().toString());
//
//                int result = num1 * num2;
//
//                setResult(RESULT_OK);
//
//            }
//        });
        ((Button) findViewById(R.id.controlSub_btn_return)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(myValue);
                int num2 = Integer.parseInt(inputText.getText().toString());

                int result = num1 + num2;

                /* 결과값을 intent에 담아서 setResult에 넘겨준다. */
                Intent intent = new Intent();   // 자원이 필요가 없다... 그래서 Context를 넘겨줄 필요가 없다.
                intent.putExtra("result", result);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        /* Bundle이 Null이면 getString()이라는 함수가 없다. */
        String value = bundle.getString("key");

        myValue = bundle.getString("myKey");







        /* 안에 코드를 봐라 Bundle이 없으면 null */
        getIntent().getStringExtra("key");





        /* 이렇게하면 필요업는 조건문이나 변수를 10번 담아야 한다. */
        /* 이럴때는 그냥 bundle을 먼저 null 처리를 하면 뒤에 getString을 할 필요가 없다. */
        String str = getIntent().getStringExtra("key");
        String str1 = getIntent().getStringExtra("key");
        String str2 = getIntent().getStringExtra("key");
        String str3 = getIntent().getStringExtra("key");

        if (getIntent().getExtras() == null) {
            /* 이렇게 하는 것이 낫다 */
        }



    }
}
