package com.rea.tours.controller;

import com.rea.tours.domain.Permission;
import com.rea.tours.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        List<Permission> permissions = permissionService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionList", permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        Permission permissions = permissionService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("permission", permissions);
        mv.setViewName("permission-show");
        return mv;
    }
}
