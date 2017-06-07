package com.doyoon.android.fastcampusandroid.week4.threadclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class ThreadClockMainActivity extends AppCompatActivity {

    private boolean runFlag = true;
    int deviceHeight, deviceWidth;
    private int centerX;
    private int centerY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        this.deviceWidth = metrics.widthPixels;
        this.deviceHeight = metrics.heightPixels;

        this.centerX = this.deviceWidth / 2;
        this.centerY = this.deviceHeight / 2;


        /* Clock을 생성한다. */
        Clock clock = new Clock();

        MyView myView = new MyView(this.getBaseContext(), clock);
        setContentView(myView);
        // setContentView(R.layout.activity_thread_clock_main);

        Thread thread = new Thread(new UpdateView(myView, clock));
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        runFlag = false;
    }

    public class UpdateView implements Runnable {

        private Clock clock;
        private View view;

        public UpdateView(View view, Clock clock) {
            this.view = view;
            this.clock = clock;
        }

        @Override
        public void run() {
            /* while - true 문 쓸때 주의할 것. */
            while(runFlag){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.clock.increase();
                this.view.postInvalidate();
            }
        }
    }

    public class MyView extends View {
        private Clock clock;
        private Paint clockPaint, msPaint, sPaint, mPaint;
        int line = 300;

        public MyView(Context context, Clock clock) {
            super(context);

            this.clock = clock;
            this.clockPaint = new Paint();
            this.clockPaint.setColor(Color.BLACK);

            this.msPaint = new Paint();
            this.msPaint.setColor(Color.RED);
            this.msPaint.setStrokeWidth(15.0f);

            this.sPaint = new Paint();
            this.sPaint.setColor(Color.GREEN);
            this.sPaint.setStrokeWidth(15.0f);

            this.mPaint = new Paint();
            this.mPaint.setColor(Color.BLUE);
            this.mPaint.setStrokeWidth(15.0f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawCircle(centerX, centerY, 20, clockPaint);
//            canvas.drawCircle(centerX, centerY, 20, clockPaint);

            /* draw clock hand */
            int msAngle = clock.getmSecond();
            drawClockHand(canvas, msPaint, msAngle*6);

            int sAngle = clock.getSecond();
            drawClockHand(canvas, sPaint, sAngle * 6);

            int mAngle = clock.getMinute();
            drawClockHand(canvas, mPaint, mAngle * 6);
        }


        public void drawClockHand(Canvas canvas, Paint paint, int inputAngle){
            int angle = inputAngle - 90;
            float endX = (float) (Math.cos(angle * Math.PI / 180) * line + centerX);
            float endY = (float) (Math.sin(angle * Math.PI / 180) * line + centerY);
            canvas.drawLine(centerX, centerY, endX, endY, paint);
        }
    }

    public class Clock {

        private int minute, second, mSecond;

        public Clock() {
            this.minute = 0;
            this.second = 0;
            this.mSecond = 0;
        }

        public void increase() {
            this.mSecond++;
            if(this.mSecond == 60){
                this.mSecond = 0;
                this.second++;
            }
            if (this.second == 60) {
                this.second = 0;
                this.minute++;
            }
            if(this.minute == 60){
                this.minute = 0;
            }
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public int getmSecond() {
            return mSecond;
        }

        public void setmSecond(int mSecond) {
            this.mSecond = mSecond;
        }
    }

}
