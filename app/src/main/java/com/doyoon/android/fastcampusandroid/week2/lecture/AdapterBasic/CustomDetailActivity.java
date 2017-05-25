package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

public class CustomDetailActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_detail);

        imageView = (ImageView) findViewById(R.id.customDetailView);
        textView = (TextView) findViewById(R.id.detailText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int postion = -1;
        if(bundle != null)
            postion = bundle.getInt(CustomListActivity.DATA_KEY);

        if (postion > -1) {         //  이렇게 처리하면 position의 값이 넘어온 뒤에만 처리를 할 수 있다.
                                    //  position이 있다는 의미는 Data가 있다는 의미로 해석할수도 있다.
            imageView.setImageResource(bundle.getInt(CustomListActivity.RESOURCE_ID));
            textView.setText(bundle.getString(CustomListActivity.DATA_TITLE));
        }


        ((Button) findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
