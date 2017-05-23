package com.doyoon.android.fastcampusandroid.week2.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.doyoon.android.fastcampusandroid.R;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        String str = "34+94*84*2*4+5-3*5/4*3*4/2+3+5+123123*124125125213/2*124123124/1/23/4+125123123/123123123125123";
        String arrStr[] = str.split("/([0-9](\\w+)*[*/][^\\-+](\\w+[*/])*[0-9](\\w+)*)+");



        for (String tempStr : arrStr) {
            Log.d("AwesomeDY", tempStr);
        }

    }


    View.OnClickListener calListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:
                    break;
            }
        }
    };
}