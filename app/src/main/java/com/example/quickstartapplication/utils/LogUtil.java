package com.example.quickstartapplication.utils;

import android.util.Log;

import com.example.quickstartapplication.BuildConfig;

public class LogUtil {
    private static final String TAG = LogUtil.class.getSimpleName();

   public static void log(String msg){
       if(BuildConfig.DEBUG) {
           Log.d(TAG, msg);
       }
   }

}
