package com.rea.tours.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
//    @RequestMapping("/")
//    public String loginContext()
//    {
//        return "login";
//    }

    @RequestMapping("/imageCode")
    public String imageCode()
    {
        return "imageCode";
    }

}
