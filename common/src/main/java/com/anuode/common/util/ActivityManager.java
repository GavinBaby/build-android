package com.anuode.common.util;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jiaqiusheng on 2015/12/30.
 */
public class ActivityManager{

    /**
     * 管理所有的activity实例
     */
    private List<Activity> mActivityContainer = new ArrayList<>();
    private static ActivityManager mActivityManager;
    private boolean logoutFlag = false;

    private ActivityManager() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static ActivityManager getInstance() {
        if (null == mActivityManager) {
            mActivityManager = new ActivityManager();
        }
        return mActivityManager;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        mActivityContainer.add(activity);
    }

    public List<Activity> getmActivityContainer() {
        return mActivityContainer;
    }

    public boolean isLogoutFlag() {
        return logoutFlag;
    }

    public void setLogoutFlag(boolean logoutFlag) {
        this.logoutFlag = logoutFlag;
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : mActivityContainer) {
            activity.finish();
        }
//        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    //退出当前登陆，返回到登陆页
    public void logout(){
        for (Activity activity : mActivityContainer) {
            activity.finish();
        }
    }
}
