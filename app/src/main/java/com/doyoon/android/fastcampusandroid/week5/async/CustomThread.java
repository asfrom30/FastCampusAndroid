package com.doyoon.android.fastcampusandroid.week5.async;

import android.os.Handler;

/**
 * Created by DOYOON on 6/12/2017.
 */

public class CustomThread extends Thread {

    private Handler handler;

    public CustomThread(Handler handler) {
        this.handler = handler;
    }

    public void run(){
        try {
            Thread.sleep(10000);
            handler.sendEmptyMessage(ThreadAsyncTaskMain.SET_DONE);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}
