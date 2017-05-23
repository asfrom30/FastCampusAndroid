package com.doyoon.android.fastcampusandroid.week2.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

public class CalculatorConstraintsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    TextView preView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_constraints);

        textView = (TextView) findViewById(R.id.textView7);
        preView = (TextView) findViewById(R.id.textView8);

        //TODO 반복문으로 처리할 것.
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);


        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnDiff).setOnClickListener(this);
        findViewById(R.id.btnMulti).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);

        findViewById(R.id.btnResult).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0 : setPreview(0); break;
            case R.id.btn1 : setPreview(1); break;
            case R.id.btn2 : setPreview(2); break;
            case R.id.btn3 : setPreview(3); break;
            case R.id.btn4 : setPreview(4); break;
            case R.id.btn5 : setPreview(5); break;
            case R.id.btn6 : setPreview(6); break;
            case R.id.btn7 : setPreview(7); break;
            case R.id.btn8 : setPreview(8); break;
            case R.id.btn9 : setPreview(9); break;

            case R.id.btnAdd : setPreview("+"); break;
            case R.id.btnDiff : setPreview("-"); break;
            case R.id.btnDiv : setPreview("/"); break;
            case R.id.btnMulti : setPreview("*"); break;

            case R.id.btnClear : clear(); break;

            case R.id.btnResult : result(); break;

        }
    }

    private void setPreview(int number){
        String current = preView.getText().toString();
        preView.setText(current + number);

    }

    private void setPreview(String str){
        String current = preView.getText().toString();
        preView.setText(current + str);
    }

    public void clear(){
        preView.setText("0");
        textView.setText("0");
    }

    public void result(){
    }
}
