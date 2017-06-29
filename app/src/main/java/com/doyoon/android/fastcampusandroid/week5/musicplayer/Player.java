package com.doyoon.android.fastcampusandroid.week5.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

/**
 * Created by DOYOON on 6/16/2017.
 */


public class Player {

    public static final int STOP = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;

    /* 현재 재생중인지, 퍼즈인지, 스탑인지 확인하는 역할을 하는 플래그를 만든다. */
    public static int playerStatus = STOP;

    public static  MediaPlayer player = null;


    public static void play(Uri musicUri, Context context){

        /* 이게 없으면 플레이를 두번 누르면 오디오가 입혀져 버린다 */
        if (player != null) {
            player.release();           /* Destory 호출 시점에 */
        }
        Log.e("MUSIC URI", musicUri.toString());
        player = MediaPlayer.create(context, musicUri);

        //2. 설정
        player.setLooping(false); // 반복여부

        //3. 시작
        player.start();
        playerStatus = PLAY;

        /*
        Music.Item item = musicList.get(position);
        Log.e("음악을 재생합니다.", "position : " + position + ", id : " + item.getId() );
        Log.e("MUSIC URI", item.getMusicUri().toString());
        Log.e("ALBUM URI", item.getAlbumArtUri().toString());
        */
    }

    public static void pause(){
        player.pause();
        playerStatus = PAUSE;
    }

    public static void replay(){
        /* 세팅은 다되어 있으니까 start만 호출해주면 된다. */
        player.start();
        playerStatus = PLAY;
    }

    /* null 체크를 해줘야 겠네.. */
    public static int getEndDuration(){
        if (player != null) {
            return player.getDuration();
        } else {
            return 0;
        }
    }

    public static int getCurrentDuration(){
        if (player != null) {
            return player.getCurrentPosition();
        } else {
            return 0;
        }
    }
}
