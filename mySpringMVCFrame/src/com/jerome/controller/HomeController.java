package com.jerome.controller;

import com.jerome.annotation.Autowired;
import com.jerome.annotation.Controller;
import com.jerome.annotation.RequestMapping;
import com.jerome.bean.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    @Autowired
    HomeService homeService;
    @RequestMapping(path = "/home",method = "get")
    public ModelAndView home(){
        String ss=homeService.getHome();
     return  new ModelAndView("home.jsp").addData("message",ss);
    }

    @RequestMapping(path = "/ajax",method = "get")
    public String ajaxhome(){
        String ss=homeService.getHome();
        return  ss;
    }

    @RequestMapping(path = "/login",method = "get")
    public String login(HttpServletRequest request){
        String ss=request.getParameter("name");
        System.out.println(ss);
        return  ss;
    }
}
