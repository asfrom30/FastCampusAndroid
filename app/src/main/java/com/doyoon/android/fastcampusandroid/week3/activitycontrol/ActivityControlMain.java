package com.doyoon.android.fastcampusandroid.week3.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;

public class ActivityControlMain extends AppCompatActivity implements View.OnClickListener{

    private Intent intentForSubActivity;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_main);

        this.intentForSubActivity = new Intent(this, ActivityControlSub.class);

        ((Button) findViewById(R.id.controlMain_btn_start)).setOnClickListener(this);
        ((Button) findViewById(R.id.controlMain_btn_result)).setOnClickListener(this);
        inputText = (EditText) findViewById(R.id.controlMain_editText_input);
    }

    public static final int BTN_RESULT = 99;
    public static final int BTN_START = 98;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // 일반적은 Activity Start
            case R.id.controlMain_btn_start :
                intentForSubActivity.putExtra("key", "From button result");
                intentForSubActivity.putExtra("myKey", inputText.getText().toString());
                startActivityForResult(intentForSubActivity, BTN_START);
                break;

            // 값을 돌려받는 Acitivity start
            case R.id.controlMain_btn_result :
                /* 결과값을 돌려받는 code */
                intentForSubActivity.putExtra("key", "From button result");
                intentForSubActivity.putExtra("myKey", inputText.getText().toString());
                startActivityForResult(intentForSubActivity, BTN_RESULT);
                // SubActivity.finisih() > 결과값을 돌려준다. > MainActivity.onActivityReslut(결과값)
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data/*결과값이 담겨 온다*/) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case BTN_RESULT :
                    int result = data.getIntExtra("result", 0/* Default Value */);
                    Toast.makeText(this, result + "Result 버튼을 눌렀다가 돌아옴", Toast.LENGTH_SHORT).show();
                    break;
                case BTN_START:
                    Toast.makeText(this, "Start 버튼을 눌렀다가 돌아옴", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
