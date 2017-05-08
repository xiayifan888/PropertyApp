package com.glory.bianyitong.util;

import android.util.Log;

/**
 * Created by Lynn on 2016/3/2 0002.
 */
public class LogUtils {
    private static final String TAG = "LogUtil";
    private static final int VERBOSE = 0;
    private static final int DEBUG = 1;
    private static final int INFO = 2;
    private static final int WARN = 3;
    private static final int ERROR = 4;
    private static final int ASSET = 5;
    private static int LOGLEVEL = 6;

    public static void v(String tag, String Msg){
        if(VERBOSE<LOGLEVEL){
            Log.v(tag,Msg);
        }

    }

    public static void d(String tag, String msg) {
        if(DEBUG < LOGLEVEL) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if(INFO < LOGLEVEL) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if(WARN < LOGLEVEL) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if(ERROR < LOGLEVEL) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, Exception e) {
        if(ERROR < LOGLEVEL) {
            Log.e(tag, e.getMessage());
        }
    }


}
