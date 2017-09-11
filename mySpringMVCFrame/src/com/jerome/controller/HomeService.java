package com.jerome.controller;

import com.jerome.annotation.Service;

@Service
public class HomeService {

    public String getHome(){

        return "hello";
    }
}
