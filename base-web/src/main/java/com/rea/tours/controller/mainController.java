package com.rea.tours.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController
{
//    @RequestMapping("/login")
//    public String loginContext(Model model)
//    {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal != null)
//        {
//            if(principal instanceof UserDetails){
//                UserDetails userDetails=(UserDetails) principal;
//                String username=userDetails.getUsername();
//                model.addAttribute("username",username);
//            }
//        }
//        return "login";

    @RequestMapping("/imageCode")
    public String imageCode()
    {
        return "imageCode";
    }

}
