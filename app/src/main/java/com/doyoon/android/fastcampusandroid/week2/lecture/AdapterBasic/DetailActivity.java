package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

public class DetailActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView) findViewById(R.id.detailText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // key 이름은 대부분 상수로 만든다.(key이름이 길어지면 보내는측과 받는측이 달라지면 에러가 나므로 보내는측에서 상수로 정의하도록한다.
        // String result = bundle.getString(AdapterActivityMain.DATA_KEY); // 이렇게 코드를 짜면 bundle이 null인 경우 에러가 출력된다. 에러처리를 해야한다.
        String result = "";
        if(bundle != null)  // 모든 페이지에서 에러 처리는 꼭 해야 한다.
            result = bundle.getString(ListActivity.DATA_KEY);

        textView.setText(result);

        ((Button) findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 특별하지 않는한 이방법으로 사용하면 안된다.
                Intent intent = new Intent(DetailActivity.this, ListActivity.class);
                startActivity(intent);      // StartActivity로 호출하면 Stack에 계속 쌓인다.

                // 이 방법을 사용해야 한다.
                // finish 로 종료하면 어짜피 Stack에 쌓인 것이 없어지므로 뒤로가기가 된다.
                finish();
            }
        });
    }
}
