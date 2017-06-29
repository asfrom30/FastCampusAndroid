package com.doyoon.android.fastcampusandroid.week5.musicplayer;

import android.os.Handler;
import android.os.Message;

/**
 * Created by DOYOON on 6/16/2017.
 */


/* Async 태스크로 변경해서 */
public class SeekBarThread extends Thread {

    Handler handler;


    boolean runFlag = true; /* Seekbar Thread의 생명주기를 하나 만든다!!!!!!!! */

    public SeekBarThread(Handler handler) {
        /* Seekbar를 어떻게 받는게 좋을까?
        * 인터페이스를 통해서 받는게 좋을 것 같다. 동작안함 UI 스레드에 접근 못함. */
        this.handler = handler;
    }

    @Override
    public void run() {
        while (true) {
            // 매초마다 음원의 실행 영역을 가져와서 식바의 위치를 변곃애준다.
            int current = Player.getCurrentDuration();  /* 같은 메모리에 올라가있어서 이렇게 쉽게 접근이 가능하구나... */
            Message msg = new Message();
            msg.what = DetailFragment.CHANGE_SEEKBAR;
            msg.arg1 = current;
            handler.sendMessage(msg);

            // 플레이 시간이 종료되면 생명주기를 끝낸다.
            /* 생명주기를 관리하는 함수를 또 만들어야 된다. */
            if (current >= Player.getCurrentDuration()) {
                runFlag = false;
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
    /* seek bar를 인터페이스로 받겠다. */
    public interface Callback{
    }
}
