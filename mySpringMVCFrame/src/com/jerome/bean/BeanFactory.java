package com.jerome.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanFactory {

    /**
     * 类实例化
     * @param cal
     * @return
     */
    public  static  Object newInstance(Class<?> cal){

        Object  instance=null;
        try {
            instance = cal.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 方法调用
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public  static Object invokeMethod(Object obj, Method method,Object... args){

        Object result=null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 设置属性值
     * @param obj
     * @param field
     * @param value
     */
    public  static  void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
