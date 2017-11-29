package com.example.administrator.all.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 缓存软件的一些参数
 */

public class CacheUtils {
    public static boolean getBoolean(Context context, String key) {

        SharedPreferences sp=context.getSharedPreferences("atiguigu", Context.MODE_PRIVATE);

        return sp.getBoolean(key,false);
    }

    /**
     * 保存
     * @param context
     * @param key
     * @param b
     */
    public static void putBoolean(Context context, String key, boolean b) {
        SharedPreferences sp=context.getSharedPreferences("atiguigu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,b).commit();
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp=context.getSharedPreferences("atiguigu", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sp=context.getSharedPreferences("atiguigu", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
