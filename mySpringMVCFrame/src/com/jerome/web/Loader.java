package com.jerome.web;

import com.jerome.bean.BeanContainer;
import com.jerome.helper.Classhelper;
import com.jerome.helper.ControllerHelper;
import com.jerome.helper.IocHelper;
import com.jerome.utils.ClassUtil;


public class Loader {
    public static void init(){

        Class<?>[] cs ={Classhelper.class, BeanContainer.class, IocHelper.class, ControllerHelper.class};

        for(Class<?> c:cs){
            System.out.println(c.getName());
            ClassUtil.loadClass(c.getName(),true);
        }
    }
}
