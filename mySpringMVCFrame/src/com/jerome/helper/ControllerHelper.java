package com.jerome.helper;

import com.jerome.annotation.RequestMapping;
import com.jerome.bean.Handler;
import com.jerome.bean.Request;
import com.jerome.utils.ArrayUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 映射器：===处理请求和请求之间的映射关系

 */
public class ControllerHelper {
    private  static  final Map<Request,Handler> requestMap = new HashMap<>();

    static {
        ArrayList<Class<?>> conClasses = Classhelper.getControllerClasses();
        if(conClasses!=null && conClasses.size()>0){
            init(conClasses);
        }
    }

    public static void init(ArrayList<Class<?>> conClasses){
        for (Class<?> c:conClasses){

            Method[] methods = c.getDeclaredMethods();
            if(ArrayUtil.isNotEmpty(methods)){
                for(Method m: methods){
                     if(m.isAnnotationPresent(RequestMapping.class)){
                         RequestMapping rm = m.getAnnotation(RequestMapping.class);
                         //拿到请求方法和请求路径
                         Request request = new Request(rm.method(),rm.path());
                         //与请求方法对应的方法以及所属的类，处理器
                         Handler handler = new Handler(c,m);
                         //在请求和处理请求之间建立映射关系
                         requestMap.put(request,handler);

                     }

                }
            }
        }

    }

    /**
     * 获取handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    //根据请求得到处理器
    public static  Handler getHander(String requestMethod,String requestPath){

        Request request = new Request(requestMethod,requestPath);
        return requestMap.get(request);
    }
}
