package com.rea.tours.service;

import com.rea.tours.domain.Permission;
import com.rea.tours.domain.Role;

import java.util.List;

public interface IRoleService
{
    List<Role> findAll();

    void save(Role role);


    List<Permission> findOtherPermission(String roleid);

    Role findById(String roleid);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
