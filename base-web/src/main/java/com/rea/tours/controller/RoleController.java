package com.rea.tours.controller;


import com.rea.tours.domain.Permission;
import com.rea.tours.domain.Role;
import com.rea.tours.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController
{
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {
        List<Role> roles = roleService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String roleid){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleid);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role)
    {
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleid){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleid);
        mv.addObject("role", role);
        List<Permission> otherPermissions = roleService.findOtherPermission(roleid);
        mv.addObject("permissionList", otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //给角色添加权限
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId,
                                      @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll";
    }

}
