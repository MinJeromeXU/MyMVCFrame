package com.jerome.utils;

import org.apache.commons.lang3.ArrayUtils;


public class ArrayUtil {

    public  static  boolean isEmpty(Object[] list){

      return ArrayUtils.isEmpty(list);
    }



    public  static  boolean isNotEmpty(Object[] list){

        return !isEmpty(list);

    }
}
