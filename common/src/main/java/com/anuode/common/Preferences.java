package com.anuode.common;

import com.anuode.common.util.PreferenceUtil;

public final class Preferences {

    // 程序上是否首次启动
    public static boolean isFirstBoot() {
        return PreferenceUtil.getBoolean(true);
    }
    public static void setFirstBoot(boolean value) {
        PreferenceUtil.setBoolean(value);
    }

    // 下拉刷新，上一次刷新时间
    public static void setPullToRefreshLastFreshTime(long value) {
        PreferenceUtil.setLong(value);
    }
    public static long  getPullToRefreshLastFreshTime() {
        return PreferenceUtil.getLong();
    }

    //记住登录状态
    public static void setLoginUsername(String username){
        PreferenceUtil.setString(username,"username");
    }
    public static void setLoginPassword(String password){
        PreferenceUtil.setString(password,"password");
    }
    public static String getLoginUsername(){
        return PreferenceUtil.getString(null,"username");
    }
    public static String getLoginPassword(){
        return PreferenceUtil.getString(null,"password");
    }
}
