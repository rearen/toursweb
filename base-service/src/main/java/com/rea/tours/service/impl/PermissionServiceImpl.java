package com.rea.tours.service.impl;

import com.rea.tours.dao.IPermissionDao;
import com.rea.tours.domain.Permission;
import com.rea.tours.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService
{
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll()
    {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission)
    {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) throws Exception
    {
        return permissionDao.findById(id);
    }

    @Override
    public List<Permission> loadPermission(String username)
    {
        return permissionDao.loadPermission(username);
    }
}
