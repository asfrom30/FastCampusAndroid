package com.doyoon.android.fastcampusandroid.week3.runtimepermission.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by DOYOON on 6/1/2017.
 */


public class PermissionUtil {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean hasPermissionAndRequestIfNot(Activity activity, String permission, int requestCode) {
        Context context = activity.getBaseContext();

        if(context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED){
            //TODO 여기에 함수를 넣고 싶은데 그게 안되서... 함수 지향 언어가 생긴건가..
            // run();
            return true;
        } else {
            // 2. 권한이 없으면 사용자에 권한을 달라고 요청
            String permissions[] = {Manifest.permission.READ_CONTACTS};
            activity.requestPermissions(permissions, requestCode); // -> 권한을 요구하는 팝업이 사용자 화면에 요청된다.
            return false;
        }
    }
}
