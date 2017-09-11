package com.jerome.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 */
public class Propsutil {

    /**
     * 加载properties文件
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream is =null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(is == null){
                throw  new FileNotFoundException(fileName+"不存在");
            }
            properties = new Properties();
            properties.load(is);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return properties;
        }

    }

    /**
     * 获取key 值
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties,String key){

        return  properties.getProperty(key);
    }


    public static String getString(String key){

        return getString(loadProps("config.properties"),key);
    }



    public static void main(String[] args){


       Properties properties = Propsutil.loadProps("config.properties");
        String value = Propsutil.getString(properties,"app.jsp_path");
        System.out.println(value);
    }
}
