package com.jerome.helper;

import com.jerome.annotation.Autowired;
import com.jerome.bean.BeanContainer;
import com.jerome.bean.BeanFactory;
import com.jerome.utils.ArrayUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 遍历BeanContainer中的Bean，看是否有Bean中有Autowired注解的属性，
 * 如果有,从BeanContainer中拿到他的class,
 * 将class注入到被注解的那个属性
 * 
 */
public final class IocHelper {
    static {
        Map<String,Object> beanContainer = BeanContainer.getBeanContainer();
        if(beanContainer!=null && beanContainer.size()>0){
            initIOC(beanContainer);
        }
    }

    private  static  void initIOC(Map<String,Object> beanContainer){

        for (Map.Entry<String,Object> entry:beanContainer.entrySet()){
            String calassName = entry.getKey();
            Object beanInstance = entry.getValue();
            Class<?> beanClass= null;

            try {
                beanClass=  Class.forName(calassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Field[] beanFileds = beanClass.getDeclaredFields();
            if(ArrayUtil.isNotEmpty(beanFileds)){
                for (Field f:beanFileds){
                    if(f.isAnnotationPresent(Autowired.class)){
                        Class<?> beanFieldClass = f.getType();
                        Object beanFieldInstance  = beanContainer.get(beanFieldClass.getName());
                        if(beanFieldInstance!=null){
                            BeanFactory.setField(beanInstance,f,beanFieldInstance);
                        }
                    }
                }

            }
        }
    }
}
