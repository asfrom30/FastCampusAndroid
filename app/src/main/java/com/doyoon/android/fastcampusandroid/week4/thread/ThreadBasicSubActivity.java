package com.doyoon.android.fastcampusandroid.week4.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.doyoon.android.fastcampusandroid.R;

public class ThreadBasicSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_basic_sub);

        // 두개 차이 안에 코드를 조금 뜯어봐야 할 것 같다.
        /* 첫번째 방법 */
        // 쓰레드 생성
        Thread thread = new Thread(){
            @Override
            public void run() {
                Log.i("thread test", "Hello Thread!!");
            }
        };

        // 쓰레드 실행
        thread.start(); // run() 함수를 실행시켜준다.

        /* 두번째 방법 */
        Runnable thread2 = new Runnable() {
            @Override
            public void run() {
                Log.i("thread test", "Hello Runnalbe!!");
            }
        };

        (new Thread(thread2)).start();

        /* 세번째 방법 */
        Thread thread3 = new Thread(new CustomThread());
        thread3.start();
    }

    // 쓰레드의 실행순서는 매번 바뀐다.
    // 처음부터 Thread만 사용하겠다고 하면 extends로 받아오면 상관 없으나... 설계를 하면서 추가되는경우 implements로 구현
    // TODO 이것도 설계를 어떤 관점에서 했는지 안에 들어가서 한번 봐야겟네...
    class CustomThread implements Runnable {

        @Override
        public void run() {
            Log.i("thread test", "Hello Custom Thread!!");
        }
    }
}
