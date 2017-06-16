package com.doyoon.android.fastcampusandroid.week5.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.doyoon.android.fastcampusandroid.R;

public class ServiceMain extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);


        ((Button) findViewById(R.id.startActivity)).setOnClickListener(this);
        ((Button) findViewById(R.id.endActivity)).setOnClickListener(this);
        ((Button) findViewById(R.id.bind)).setOnClickListener(this);
        ((Button) findViewById(R.id.unbind)).setOnClickListener(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()) {
            case R.id.startActivity:
                startService(intent);
                break;
            case R.id.endActivity:
                stopService(intent);
                break;
            case R.id.bind:
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(connection);
                break;
        }
    }

    /* 서비스에서 뭔가 결과처리를 하거나 데이터를 가져오게 되었는데, 값을 참조하기 위해서 IBinder라는 인터페이스를 사용
    * Activity와 Serivce사이에 의존성이 전혀 없기때문에... */
    ServiceConnection connection = new ServiceConnection() {
        // 서비스와 연결되는 순간 호출.....
        // onBind가 호출되면서 이쪽 param으로 onBind param을 넘겨주게 된다.
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder /* 이름 변경 */) {
            Log.d("MainActivity", "onServiceConnected");

            // 바인더를 통해 서비스에 접근한다.
            MyService.MyBinder myBinder = (MyService.MyBinder) binder;
            MyService service = myBinder.getService();
            service.print("출력되었습니다.");

        }

        // 일반적인 상황에서 호출되지 않는다... 거의 쓸일이 없다?? onDestroy이에서는 호출되지 않는다.
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MainActivity", "onServiceDisConnected");
        }
    };
}
