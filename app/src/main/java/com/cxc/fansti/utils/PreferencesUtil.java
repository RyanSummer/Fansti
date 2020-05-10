package com.cxc.fansti.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 偏好设置
 */
public class PreferencesUtil {

    public static final String NAME = "config.sp";

    /**
     * 存入数据(String)
     */
    public static void putString(Context context, String key, String value) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        //向偏好设置内部添加数据并且提交
        spf.edit().putString(key, value).commit();
    }

    /**
     * 取数据(上下文，钥匙，未取得数据返回的默认值)(String)
     */
    public static String getString(Context context, String key, String value) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        //返回需要取得的数据
        return spf.getString(key, value);
    }

    /**
     * 存入数据(Int)
     */
    public static void putInt(Context context, String key, int value) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        //向偏好设置内部添加数据并且提交
        spf.edit().putInt(key, value).commit();
    }

    /**
     * 取数据(上下文，钥匙，未取得数据返回的默认值)(Int)
     */
    public static int getInt(Context context, String key, int value) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        //返回需要取得的数据
        return spf.getInt(key, value);
    }

    /**
     * 存入数据(Int)
     */
    public static void putBoolean(Context context, String key, boolean value) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        //向偏好设置内部添加数据并且提交
        spf.edit().putBoolean(key, value).commit();
    }

    /**
     * 取数据(上下文，钥匙，未取得数据返回的默认值)(Int)
     */
    public static boolean getBoolean(Context context, String key, boolean value) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        //返回需要取得的数据
        return spf.getBoolean(key, value);
    }

    /**
     * 删除单个偏好设置
     */
    public static void deleteShare(Context context, String key) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spf.edit().remove(key).commit();
    }
    /**
     * 删除单个偏好设置
     */
    public static void deleteShareAll(Context context, String key) {
        //获得偏好设置
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spf.edit().clear().commit();
    }

}
