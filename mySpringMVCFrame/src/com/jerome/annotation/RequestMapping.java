package com.jerome.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    enum  RequestMethod{
        GET,
        POST,
        PUT,
        DELETE
    }

    String path();
    String method();
}
