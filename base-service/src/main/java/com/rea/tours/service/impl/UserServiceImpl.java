package com.rea.tours.service.impl;

import com.rea.tours.dao.IUserDao;
import com.rea.tours.domain.Role;
import com.rea.tours.domain.UserInfo;
import com.rea.tours.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService
{
    @Resource
    private IUserDao userdao;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserInfo> findAll()
    {

        return userdao.findAll();
    }

    @Override
    public void save(UserInfo user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userdao.save(user);
    }

    @Override
    public UserInfo findById(String id)
    {
        return userdao.findById(id);
    }

    @Override
    public List<Role> findOtherRole(String userid)
    {
        return userdao.findOtherRole(userid);
    }

    @Override
    public void addRoleToUser(String userid, String[] roleIds)
    {
        for (String roleId:roleIds){
            userdao.addRoleToUser(userid, roleId);
        }
    }

    @Override
    public UserInfo findByUsername(String username) throws Exception
    {
        return userdao.findByUsername(username);
    }
}
