package com.anuode.common.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflector {
    public static Object getFieldValue(Class clazz, Object object, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean setFieldValue(Class clazz, Object object, String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Object invokeMethod(Class<?> clazz, Object object, String methodName, final Class<?>[] paramClasses, Object[] params) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, paramClasses);
            method.setAccessible(true);
            return method.invoke(object, params);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Object invokeMethod(Class<?> clazz, Object object, String methodName) {
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            method.setAccessible(true);
            return method.invoke(object);
        } catch (Exception ex) {
            return null;
        }
    }
}
