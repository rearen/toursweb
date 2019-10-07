package com.rea.tours.service.impl;

import com.rea.tours.dao.IUserDao;
import com.rea.tours.domain.Role;
import com.rea.tours.domain.UserInfo;
import com.rea.tours.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService
{
    @Autowired
    private IUserDao userdao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserInfo userInfo = null;
        try
        {
            userInfo = userdao.findByUsername(username);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //把自己的用户对象封装成UserDetails
        //User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true, true, true,
                true, getAuthority(userInfo.getRoles()));
//        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(),
//                userInfo.getStatus() == 0 ? false : true, true, true,
//                true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles)
    {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles)
        {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll()
    {

        return userdao.findAll();
    }

    @Override
    public void save(UserInfo user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
}
