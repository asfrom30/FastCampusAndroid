package com.doyoon.android.fastcampusandroid.week4.thread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadBasicRainActivity extends AppCompatActivity {

    FrameLayout ground;
    Stage stage;

    int deviceWidth, deviceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_basic_rain);

        /* device width height를 가져온다. */
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;

        ground = (FrameLayout) findViewById(R.id.threadBasic_stage);

        stage = new Stage(getBaseContext());

        ground.addView(stage);

        // 여기에 선언을 하면 화면이 그려지기 전에 밑에 for문을 호출해버린다. 그래서 쓰레드로 만들어야 한다.
        /*
        for(int i=0; i<1000; i++) {
            rainDrop.y = rainDrop.speed * i;
            // Ondraw 함수를 호출해준다.
            stage.invalidate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */

        ((Button) findViewById(R.id.threadBasic_btn_run)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runTast();
            }
        });

    }

    private void runTast() {
        // Thread로 변경
        Rain rain = new Rain();
        rain.start();

        DrawCanvas drawCanvas = new DrawCanvas();
        drawCanvas.start();
    }

    class Stage extends View {

        Paint paint;
        RainDrop rainDrop = null;
        List<RainDrop> rainDropList = new ArrayList<>();

        /* context = 시스템 자원을 쓰겠다. */
        public Stage(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
        }

        /* 화면에 로드되는 순간 호출되는 함수 */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
//            if (rainDrop != null) {
//                canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.radius, rainDrop.paint);
//            }
            for (RainDrop rainDrop : rainDropList) {
                canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.radius, rainDrop.paint);
            }
        }

        public void addRainDrop(RainDrop rainDrop) {
            this.rainDropList.add(rainDrop);
        }
    }

    /* Rain Drop이 쓰레드를 가지게 된다. 자신의 생명주기를 가지고 동작 */
    class RainDrop extends Thread{
        float radius;
        Paint paint;
        int speed;
        float x;
        float y;

        /* 끝나면 해당 쓰레드가 죽었다는것을 알려주는 Flag */
        boolean run = true;

        @Override
        public void run() {
            int count = 0;
            while (y<deviceHeight) {
                count++;
                y = count * speed;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            run = false;
        }
    }

    // 화면을 1초에 한번씩 그려주는 클래스 (onDraw를 호출)
    class DrawCanvas extends Thread {
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stage.postInvalidate();
            }
        }
    }


    class Rain extends Thread {
        @Override
        public void run() {

            Random random = new Random();

            super.run();

            for(int j=0; j < 100; j++) {
                // 빗방울 하나 생성
                RainDrop rainDrop = new RainDrop();


                rainDrop.radius = random.nextInt(20)+5;

                rainDrop.x = random.nextInt(deviceWidth);


                rainDrop.y =0f;

                rainDrop.speed = random.nextInt(10)+ 5; // 초당 이동하는 거리 5~15
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);
                rainDrop.paint = paint;

                // stage가 필요
                stage.addRainDrop(rainDrop);

                rainDrop.start();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /*
            for(int i=0; i<1000; i++) {
                rainDrop.y = rainDrop.speed * i;

//                stage.invalidate(); // Ondraw 함수를 호출해준다. - Main Thread에서 동작할때
                stage.postInvalidate(); // Ondraw 함수를 호출해준다. - 다른 Thread에서 동작할때
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            */
        }
    }

}
