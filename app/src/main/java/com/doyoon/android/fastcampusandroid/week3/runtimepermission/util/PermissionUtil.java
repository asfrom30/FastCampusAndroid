package com.doyoon.android.fastcampusandroid.week3.runtimepermission.util;

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
    public static boolean hasPermissionsAndRequestIfNot(Activity activity, String[] permissions, int requestCode) {
        Context context = activity.getBaseContext();

        for (String permission : permissions) {
            if(context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                activity.requestPermissions(permissions, requestCode);
                return false;
            }
        }
        return true;
    }
}
