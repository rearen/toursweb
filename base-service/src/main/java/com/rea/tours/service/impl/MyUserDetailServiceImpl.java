package com.rea.tours.service.impl;

import com.rea.tours.dao.IUserDao;
import com.rea.tours.domain.Role;
import com.rea.tours.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("myUserDetailService")
public class MyUserDetailServiceImpl implements UserDetailsService
{

    @Autowired
    private IUserDao userdao;

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
            throw new UsernameNotFoundException(username+" not exist!");
        }
//        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
//        SimpleGrantedAuthority auth2=new SimpleGrantedAuthority("ROLE_ADMIN");
//        SimpleGrantedAuthority auth1=new SimpleGrantedAuthority("ROLE_USER");
//        if(username.equals("tom")){
//
//            auths=new ArrayList<GrantedAuthority>();
//            auths.add(auth1);
//            auths.add(auth2);
//        }
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();


        //把自己的用户对象封装成UserDetails
//        User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true, true, true,
                true, getAuthority(userInfo.getRoles()));
//        User user = new User(username, "123",
//                true, true, true,
//                true, auths);
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
}
