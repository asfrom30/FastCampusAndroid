package com.doyoon.android.fastcampusandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by DOYOON on 6/15/2017.
 */


public class PermissionControl {
    private static final String TAG = PermissionControl.class.getName();
    private static final int REQ_CODE = 291623;

    public static void requestAndRunOrNot(Activity activity, Callback callback, String[] permissions){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PermissionControl.request(activity, callback, permissions);
        } else {
            callback.run();
        }
    }

    /* Overload requestAndRunOrNot()*/
    /* Activity랑 Callback을 따로받는것이 아니라 동시에 받는다. istanceof로 체크하고 런타임 퍼미션 */
    public static void requestAndRunOrNot(Activity activity, String[] permissions){
        if(activity instanceof  Callback){
            throw new RuntimeException("Must Implement PermissionControl.CallBack");
        }

        Callback callback = (Callback) activity;
        requestAndRunOrNot(activity, callback, permissions);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)   /* 해당함수는 마쉬멜로우 버전(23) 이상에서만 실행이 됩니다. */
    private static void request(Activity activity, Callback callback, String[] permissions){

        boolean hasPermissions = true;

        Context context = activity.getBaseContext();

        /* 필요한 권한중에 하나라도 없으면 hasPersmission == false */
        for (String permission : permissions) {
            if(context.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED){
                hasPermissions = false;
                break;
            }
        }

        if(hasPermissions){
            callback.run();
        } else {
            activity.requestPermissions(permissions, REQ_CODE); // Request Permission POP-UP
        }
    }

    public static void postPermissionResult(Callback callback, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

        if (requestCode != REQ_CODE) {
            Log.e(TAG, "REQUEST CODE를 확인해주세요");
        }

        boolean granted = true;

        for (int isGrant : grantResults) {
            if(isGrant != PackageManager.PERMISSION_GRANTED){
                granted = false;
                break;
            }
        }
        if(granted) callback.run();
        else callback.cancle();
    }

    public interface Callback {
        public void run();
        public void cancle();
    }

}
