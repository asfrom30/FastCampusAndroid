package com.doyoon.android.fastcampusandroid.week5.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
        Log.i("MyService", "Constructor");
    }

    @Override
    public void onCreate() {
        Log.i("MyService", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyService", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyService", "onUnbind");
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        Log.i("MyService", "onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService", "onBind");
        return new MyBinder();
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void print(String string) {
        Log.i("MyService", string);
    }

    /* 안에 정의된 함수를 메인액티비티(서비스를 호출 한 곳에서) 직접 호출 할 수 있다. .. */
    public class MyBinder extends Binder {

        /* 특이한 패턴.. 이다. */
        /* 현재 생성된 서비스를 넘겨주겠다.*/
        public  MyService getService(){
            return MyService.this;
        }
    }

}
