package com.rea.tours.controller;

import com.rea.tours.domain.Role;
import com.rea.tours.domain.UserInfo;
import com.rea.tours.service.IRoleService;
import com.rea.tours.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private IUserService userServicer;

    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {
        List<UserInfo> users = userServicer.findAll();
        ModelAndView mv =new ModelAndView();
        mv.addObject("userList", users);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(UserInfo user)
    {
        userServicer.save(user);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id)
    {
        UserInfo user = userServicer.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid)
    {
        UserInfo user=userServicer.findById(userid);
        List<Role> otherRoles=userServicer.findOtherRole(userid);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user", user);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,
                                @RequestParam(name = "ids",required = true) String[] roleIds){
        userServicer.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }
}
