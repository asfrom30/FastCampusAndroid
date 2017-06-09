package com.doyoon.android.fastcampusandroid.week4.threadcommunication.way;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by DOYOON on 6/8/2017.
 */

public class RunOnUiThreadCounter implements Runnable {

    private Activity activity;
    private TextView textView;
    int count = 0;

    public RunOnUiThreadCounter(Activity activity, TextView textView) {
        this.activity = activity;
        this.textView = textView;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e){

            }
            // Handler for control in Sub Thread
            this.activity.runOnUiThread(new Runnable() { // 코드로 가면 이렇게 객체를 생성하지 않고 함수를 직접 넘겨주면 된다.
                @Override
                public void run() {
                    Log.i("TAG1", count+"");
                    textView.setText(count+""); // 여기는 메인스레드에서 동작한다. 코드를 넘겨준다.
                                                // 메인 스레드에 붙이기 때문에.. Sub 스레드와 타이밍이 어긋난다.
                                                // 9번째 왔을때 이 코드를 메인 스레드에 던져 놓고
                                                // count++ 실행되고 이 코드가 실행하면 10이 찍히고
                                                // count++가 실행되기 전에 이코드가 실행되면 9가 찍힌다.
                }
            });
            count ++;
        }
    }
}
