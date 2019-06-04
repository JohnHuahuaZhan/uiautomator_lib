package com.uiautomator.lib.support.log;

import android.util.Log;

public class UIAutomatorLogger {
    public static final String TAG = "uiautomator";
    public static void d(String message){
        Log.d(TAG, message);
    }
    public static void e(String message){
        Log.e(TAG, message);
    }
}
