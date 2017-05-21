package com.doyoon.android.fastcampusandroid.week2.lecture.BasicLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.fastcampusandroid.R;

public class BasicLayoutActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRelative, btnLinear, btnGrid, btnSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_layout);

        btnRelative = (Button) findViewById(R.id.btnRelative);
        btnLinear = (Button) findViewById(R.id.btnLinear);
        btnGrid = (Button) findViewById(R.id.btnGrid);
        btnSpinner = (Button) findViewById(R.id.btnSpinner);

        btnRelative.setOnClickListener(this);
        btnLinear.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
        btnSpinner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRelative:
                // intent가 목적지를 알려준다.
                Intent intent = new Intent(this, RelativeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLinear:
                break;
            case R.id.btnGrid:
                break;
            case R.id.btnSpinner:
                break;
        }
    }
}
