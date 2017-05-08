package com.glory.bianyitong.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lynn on 2016/2/23 0023.
 */
public class SharePreToolsKits {

    /*
    * 存储应用版本  sp
    * */
    public static SharedPreferences getSharedPreferencesVersion(Context context) {

        return context.getSharedPreferences("com.bianyitong.caver.version", Context.MODE_PRIVATE);
    }

    /*
    * 存储应用的一般数据  sp
    * */
    public static SharedPreferences getSharedPreferences(Context context) {

        return context.getSharedPreferences("com.bianyitong.caver", Context.MODE_PRIVATE);
    }

    /*
    * 存储用户 数据 比如账户 或者设置等
    * */
    public static SharedPreferences getSharedPreferencesUser(Context context) {

        return context.getSharedPreferences("com.bianyitong.caver.UserInfo", Context.MODE_PRIVATE);
    }


    /*
    * 该文件主要是 存储本地的一些json数据
    * */
    public static SharedPreferences getJsonDataSharedPreferences(Context context) {

        return context.getSharedPreferences("com.bianyitong.jsondata", Context.MODE_PRIVATE);
    }

    /*
    * 存储boolean值
    *
    * */
    public static void putBooble(Context context, String key, boolean value) {

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }

    /*
   * 存储 用户  boolean值
   *
   * */
    public static void putUserBooble(Context context, String key, boolean value) {

        SharedPreferences sharedPreferences = getSharedPreferencesUser(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }


    /*
     * 提取boolean值
     * */
    public static boolean fetchBooble(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    /*
  * 提取 用户 boolean值
  * */
    public static boolean fetchUserBooble(Context context, String key, boolean defaultValue) {
        return getSharedPreferencesUser(context).getBoolean(key, defaultValue);
    }

    /*
    * 存储 String 值 版本
    * */
    public static void putVersionString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferencesVersion(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /*
   * 提取 String 值 版本
   * */
    public static String fetchVersionString(Context context, String key) {
        return getSharedPreferencesVersion(context).getString(key, null);
    }

    /*
    * 存储 String 值
    * */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

     /*
    * 提取 String 值
    * */
    public static String fetchString(Context context, String key) {
        return getSharedPreferences(context).getString(key, null);
    }

     /*
    * 提取 用户  String 值
    * */
    public static String fetchUserString(Context context, String key) {
        return getSharedPreferencesUser(context).getString(key, null);
    }

     /*
    * 存储 用户 String 值
    * */

    public static void putUserString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferencesUser(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /*
   * 存储 Json  String 值
   * */
    public static void putJsonDataString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getJsonDataSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

       /*
    * 提取 json  String 值
    * */

    public static String fetchJsonDataString(Context context, String key) {
        return getJsonDataSharedPreferences(context).getString(key, null);
    }

    /*
    * 清除 sharepresfrences 数据
    * */
    public static void clearShare(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /*
    * 清除 sharepresfrences 数据 json
    * */
    public static void clearJsonDataShare(Context context) {
        SharedPreferences sharedPreferences = getJsonDataSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /*
  * 清除 sharepresfrences 的用户  数据
  * */
    public static void clearUserShare(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesUser(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
