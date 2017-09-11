package com.jerome.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 
 */
public class JsonUtil {

    public  static <T> String toJson(T obj){
        String json = null;
        ObjectMapper mapper= new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static  <T> T  toObj(String  json,Class<T> classzz){

        T pojo = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
           pojo = mapper.readValue(json,classzz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  pojo;
    }
}
