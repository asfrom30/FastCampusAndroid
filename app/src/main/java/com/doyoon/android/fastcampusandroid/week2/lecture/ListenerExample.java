package com.doyoon.android.fastcampusandroid.week2.lecture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

/**
* 클릭 리스너 구현하는 방법 세가지
* 1. 위젯을 사용하는 객체가 상속받아서 구현
* 2. 객체내에서 변수로 생성
* 3. setOnclickListenr 함수에 익명 객체로 전달(익명 객체는 식별자가 없는 것)
*    // ex) btn.setOnClickListenr(new View.OnClickListener()){}
*/
public class ListenerExample extends AppCompatActivity implements View.OnClickListener{

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // R은 Resource의 약자.
        tv = (TextView) findViewById(R.id.httpUrlConnection_tv);  // View 클래스를 상속받아서 만들어진다. 새로 캐스팅을 해야 한다.
        btn = (Button) findViewById(R.id.btnClick);

        //1번 형태로 구현
        btn.setOnClickListener(this);

        //2번 형태로 구현 - 아래에 구현된 리스너를 등록해준다.
        btn.setOnClickListener(listener);

        // 3번 형태로 구현
        // 여기가 익명 객체 형태이다.
        btn.setOnClickListener(new View.OnClickListener() { // new + O
            @Override
            public void onClick(View v) {
                tv.setText("Hello Doyoon");
            }
        });
    }

    // 1번 형태
    @Override
    public void onClick(View v) {
        tv.setText("안녕 안드로이드!!!");
    }

    // 2번 형태로 구현
    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            tv.setText("Hello Doyoon");
        }
    };
}
