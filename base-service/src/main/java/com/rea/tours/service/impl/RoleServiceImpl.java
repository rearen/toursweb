package com.rea.tours.service.impl;

import com.rea.tours.dao.IRoleDao;
import com.rea.tours.domain.Permission;
import com.rea.tours.domain.Role;
import com.rea.tours.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService
{
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(){
        return roleDao.findAll();
    }

    @Override
    public void save(Role role)
    {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId)
    {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public Role findById(String roleId)
    {
        return roleDao.findById(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds)
    {
        for(String permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
