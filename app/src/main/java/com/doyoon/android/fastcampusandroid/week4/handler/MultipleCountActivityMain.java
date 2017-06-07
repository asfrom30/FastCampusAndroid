package com.doyoon.android.fastcampusandroid.week4.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

public class MultipleCountActivityMain extends AppCompatActivity {

    TextView[] textViews;

    public static final int SET_COUNT = 99;

    //서브 thread로 부터 메세지를 전달받을 Handler를 생성한다... 메세지 통신
    Handler handler = new Handler() {

        // 서브 thread에서 메세지를 전달하면 handleMessage 함수가 동작한다.
        @Override
        public void handleMessage(Message msg) {
            // 메세지가 정해져 있따.
//            msg.arg1;
//            msg.arg2;

            // 자바에서는 주로 integer로 주고 받는다, wha
            // 핸들러간 통신을 할때는 미리 상수로 정의 해 놓는다.

            switch (msg.what) {
                case SET_COUNT:

                    break;
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_count_main);

        textViews = new TextView[4];

        for(int i =0; i < 4; i++) {
            int resId = getResources().getIdentifier("multipleCount_tv_" + (i + 1), "id", getPackageName());
            textViews[i] = (TextView) findViewById(resId);
        }
        Object syncTocken1 = new Object();
        Object syncTocken2 = new Object();
        Object syncTocken3 = new Object();
        Object syncTocken4 = new Object();


        Counter counter1 = new Counter(textViews[0], this, syncTocken1, syncTocken2);
        Counter counter2 = new Counter(textViews[1], this, syncTocken2, syncTocken3);
        Counter counter3 = new Counter(textViews[2], this, syncTocken3, syncTocken4);
        Counter counter4 = new Counter(textViews[3], this, syncTocken4, syncTocken1);

        syncTocken1.notify();
        (new Thread(counter1)).start();
        (new Thread(counter2)).start();
        (new Thread(counter3)).start();
        (new Thread(counter4)).start();


    }

    class CounterHandler extends Thread{
        private int textViewIndex;
        Handler handler;
        private Activity activity;
        TextView textView;
        int count = 0;


        public CounterHandler(int index, TextView textView, Handler handler ) {
            this.textView = textView;
            this.handler = handler;
            this.textViewIndex = index;
        }

        @Override
        public void run() {

            for(int i=0; i<10; i++) {
                // 핸들러를 통해 메세지를 날릴때 Msg를 생성해서 날릴 수 있따.

                Message msg = new Message();

                // 메세직 객체가 그렇게 설계가 되어 있다. arg1, arg2 두개의 argument를 ㄷ날려줄 수 있다.
                msg.what = SET_COUNT;
                handler.sendEmptyMessage(SET_COUNT);
                // handler.sendMessage(SET_COUNT);
                try {
                    Thread.sleep(500);
                } catch (Exception e){

                }
                count ++;
            }
        }
    }

    class Counter extends Thread{
        private Object syncToken, nextSyncTocken;
        private Activity activity;
        TextView textView;
        int count = 0;


        public Counter(TextView textView, Activity activity, Object syncToken, Object nextSyncTocken) {
            this.textView = textView;
            this.activity = activity;
            this.syncToken = syncToken;
            this.nextSyncTocken = nextSyncTocken;
        }

        @Override
        public void run() {

            /* suncronized token */
            synchronized (syncToken)
            {
                try {
                    syncToken.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (Exception e){

                }
                // Handler for control in Sub Thread
                activity.runOnUiThread(new Runnable() { // 코드로 가면 이렇게 객체를 생성하지 않고 함수를 직접 넘겨주면 된다.
                    @Override
                    public void run() {
                        textView.setText(count+""); // 여기는 메인스레드에서 동작한다. 코드를 넘겨준다.
                                                    // 메인 스레드에 붙이기 때문에.. Sub 스레드와 타이밍이 어긋난다.
                                                    // 9번째 왔을때 이 코드를 메인 스레드에 던져 놓고
                                                    // count++ 실행되고 이 코드가 실행하면 10이 찍히고
                                                    // count++가 실행되기 전에 이코드가 실행되면 9가 찍힌다.
                    }
                });
                count ++;
            }

            nextSyncTocken.notify();

        }
    }
}


