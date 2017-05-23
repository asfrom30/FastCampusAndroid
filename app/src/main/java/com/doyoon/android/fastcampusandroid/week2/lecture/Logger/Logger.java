package com.doyoon.android.fastcampusandroid.week2.lecture.Logger;

import android.util.Log;

import com.doyoon.android.fastcampusandroid.BuildConfig;

/**
 * Created by DOYOON on 5/23/2017.
 */

public class Logger {

    // release mode <-> debug mode
    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String tag, String msg){
        if(DEBUG){
            Log.d(tag,msg);
        } else {
            
        }
    }
}
