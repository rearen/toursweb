package com.rea.tours.service;

import com.rea.tours.domain.Role;
import com.rea.tours.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService
{

    List<UserInfo> findAll();

    void save(UserInfo user);

    UserInfo findById(String id);

    List<Role> findOtherRole(String userid);

    void addRoleToUser(String userid, String[] roleIds);
}
