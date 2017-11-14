package com.example.myutils.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author:wangcaiwen
 * Time:2017/11/12.
 * Description:sp的封装
 */

public class SharedPreferencesUtils {

        private static final String _DATA="sp_data";
        private static Context context;
        private static SharedPreferencesUtils mPreferencesUtils;
        private static SharedPreferences preferences;
        private static  SharedPreferences.Editor edit;

    public SharedPreferencesUtils(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(_DATA, Context.MODE_PRIVATE);
        edit = preferences.edit();
    }

    public  SharedPreferencesUtils getInstance(Context context){

            if(mPreferencesUtils==null){
                synchronized (SharedPreferencesUtils.class){
                    if (mPreferencesUtils==null){

                        mPreferencesUtils = new SharedPreferencesUtils(context);
                    }
                }
            }

            return mPreferencesUtils;
        }



    public static String getString(String key){
        return preferences.getString(key,null);
        };
    public static int getInt(String key){
        return preferences.getInt(key,-1);
    };
    public static boolean getBoolean(String key){
        return preferences.getBoolean(key,false);
    };

    public static void putString(String key,String value){
        edit.putString(key,value).commit();
    }
    public static void putInt(String key,int value){
        edit.putInt(key,value);
    }
    public static void putBoolean(String key,Boolean value){
        edit.putBoolean(key,value);
    }

}
