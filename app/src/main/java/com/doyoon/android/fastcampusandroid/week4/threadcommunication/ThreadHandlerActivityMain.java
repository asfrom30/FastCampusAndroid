package com.doyoon.android.fastcampusandroid.week4.threadcommunication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week4.threadcommunication.way.ErrorCounter;
import com.doyoon.android.fastcampusandroid.week4.threadcommunication.way.HandlerCounter;
import com.doyoon.android.fastcampusandroid.week4.threadcommunication.way.RunOnUiThreadCounter;

/**
 * Created by DOYOON on 6/8/2017.
 */

public class ThreadHandlerActivityMain extends AppCompatActivity {

    TextView[] textViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Mulitple Count UI */
        setContentView(R.layout.activity_multiple_count_main);

        /* Dependent Injection */
        textViews = new TextView[4];

        for(int i =0; i < 4; i++) {
            int resId = getResources().getIdentifier("multipleCount_tv_" + (i + 1), "id", getPackageName());
            textViews[i] = (TextView) findViewById(resId);
        }

        /* Error Counter */
        ErrorCounter errorCounter = new ErrorCounter(textViews[0]);
        new Thread(errorCounter).start();

        /* Way 1 - RunOnUiThread */
        RunOnUiThreadCounter runOnUiThreadCounter = new RunOnUiThreadCounter(this, textViews[1]);
        new Thread(runOnUiThreadCounter).start();

        /* Way 2 - Handler */
        HandlerCounter handlerCounter = new HandlerCounter(this);
        new Thread(handlerCounter).start();



    }
}
