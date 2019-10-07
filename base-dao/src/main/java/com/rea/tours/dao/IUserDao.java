package com.rea.tours.dao;

import com.rea.tours.domain.Role;
import com.rea.tours.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserDao
{
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many =
            @Many(select = "com.rea.tours.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
    })
    List<UserInfo> findAll();

    @Insert("insert into `users`(id,username,password,email,phoneNum,status) " +
            "values(replace(uuid(),'-',''),#{username}, #{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo user);

    @Select("Select * from users where id=#{id}")
    @Results({@Result(id = true, property = "id", column = "id"),
              @Result(property = "username", column = "username"),
              @Result(property = "email", column = "email"),
              @Result(property = "password", column = "password"),
              @Result(property = "phoneNum", column = "phoneNum"),
              @Result(property = "status", column = "status"),
              @Result(property = "roles", column = "id", javaType = java.util.List.class,
                      many = @Many(select = "com.rea.tours.dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findById(String id);

    @Insert("insert into users_role (userId, roleId) values (#{userId},#{roleId});")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);

    @Select("Select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRole(String userId);
}
