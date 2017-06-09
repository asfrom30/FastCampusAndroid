package com.doyoon.android.fastcampusandroid.week4.threadcommunication.way;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

/**
 * Created by DOYOON on 6/8/2017.
 */

// Handler 클래스를 전달 받아서.. 서브 thread로 부터 메세지를 전달받을 Handler를 생성한다... 메세지 통신
public class HandlerCounter extends  Handler implements Runnable{

    public static final int SET_COUNT = 99;
    private Activity activity;
    private int count = 0;

    public HandlerCounter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        for(int i=0; i < 10; i ++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 핸들러를 통해 메세지를 날릴때 Msg를 생성해서 날릴 수 있따.
            // 메세직 객체가 그렇게 설계가 되어 있다. arg1, arg2 두개의 argument를 날려줄 수 있다.
            Message msg = new Message();
            msg.what = SET_COUNT;
            msg.arg1 = count;
            // msg.arg1 = integer;
            // msg.arg2 = integer;
            // msg.setData();       // send Instance
            sendMessage(msg);
            /* Empty Message */
            // sendEmptyMessage(SET_COUNT);

            count++;
        }
    }

    // 서브 thread에서 메세지를 전달하면 handleMessage 함수가 동작한다.
    @Override
    public void handleMessage(Message msg) {
        // 메세지가 정해져 있따.
        // msg.arg1;
        // msg.arg2;

        // 자바에서는 주로 integer로 주고 받는다,
        // 핸들러간 통신을 할때는 미리 상수로 정의 해 놓는다.

        switch (msg.what) {
            case SET_COUNT:
                int tempCount = msg.arg1;
                ((TextView) activity.findViewById(R.id.multipleCount_tv_3)).setText(tempCount + "");
                break;
        }

    }
}
