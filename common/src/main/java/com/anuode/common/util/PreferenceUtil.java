package com.anuode.common.util;

import android.content.Context;
import android.content.SharedPreferences;

public final class PreferenceUtil {

    private static Context mContext = null;

    public static void init(Context context) {
        mContext = context;
    }

    public static String getPreferenceMethodName() {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String name =  stacks[3].getMethodName();
        name = name.replace("get", "");
        name = name.replace("set", "");
        name = name.replace("is", "");
        return name;
    }


    public static String getString() {
        return getString(null);
    }

    public static String getString(String defaultValue) {
        return getString(defaultValue, null);
    }

    public static String getString(String defaultValue, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        return preferences.getString(preferenceMethodName, defaultValue);
    }

    public static void setString(String value) {
        setString(value, null);
    }

    public static void setString(String value, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        SharedPreferences.Editor preferenceEditor = preferences.edit();
        preferenceEditor.putString(preferenceMethodName, value).apply();
    }

    public static int getInt() {
        return getInt(0, null);
    }

    public static int getInt(int defaultValue) {
        return getInt(defaultValue, null);
    }

    public static int getInt(int defaultValue, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        return preferences.getInt(preferenceMethodName, defaultValue);
    }

    public static void setInt(int value) {
        setInt(value, null);
    }

    public static void setInt(int value, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        SharedPreferences.Editor preferenceEditor = preferences.edit();
        preferenceEditor.putInt(preferenceMethodName, value).apply();
    }

    public static long getLong() {
        return getLong(0, null);
    }

    public static long getLong(long defaultValue) {
        return getLong(defaultValue, null);
    }

    public static long getLong(long defaultValue, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        return preferences.getLong(preferenceMethodName, defaultValue);
    }

    public static void setLong(long value) {
        setLong(value, null);
    }

    public static void setLong(long value, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        SharedPreferences.Editor preferenceEditor = preferences.edit();
        preferenceEditor.putLong(preferenceMethodName, value).apply();
    }

    public static boolean getBoolean() {
        return getBoolean(false, null);
    }

    public static boolean getBoolean(boolean defaultValue) {
        return getBoolean(defaultValue, null);
    }

    public static boolean getBoolean(boolean defaultValue, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        return preferences.getBoolean(preferenceMethodName, defaultValue);
    }

    public static void setBoolean(boolean value) {
        setBoolean(value, null);
    }

    public static void setBoolean(boolean value, String prefix) {
        String preferenceMethodName = getPreferenceMethodName();
        if (prefix != null) {
            preferenceMethodName += prefix;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(preferenceMethodName, Context.MODE_PRIVATE);
        SharedPreferences.Editor preferenceEditor = preferences.edit();
        preferenceEditor.putBoolean(preferenceMethodName, value).apply();
    }
}
