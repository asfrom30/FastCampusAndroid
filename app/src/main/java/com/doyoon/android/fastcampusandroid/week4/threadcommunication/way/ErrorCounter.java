package com.doyoon.android.fastcampusandroid.week4.threadcommunication.way;

import android.widget.TextView;

/**
 * Created by DOYOON on 6/8/2017.
 */

public class ErrorCounter implements Runnable {

    private TextView textView;
    int count = 0;

    public ErrorCounter(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e){

            }
            this.textView.setText(count+"");
            count ++;
        }
    }
}
