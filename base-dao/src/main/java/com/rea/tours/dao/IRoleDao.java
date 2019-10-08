package com.rea.tours.dao;

import com.rea.tours.domain.Permission;
import com.rea.tours.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao
{
    @Select("select * from role")
    public List<Role> findAll();

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({@Result(id = true,property = "id",column = "id"),
              @Result(property = "roleName",column = "roleName"),
              @Result(property = "roleDesc",column = "roleDesc"),
              @Result(property = "permissions",column = "id",javaType = java.util.List.class,many=
              @Many(select = "com.rea.tours.dao.IPermissionDao.findPermissionByRoleId"))})
    public List<Role> findRoleByUserId(String userId) throws Exception;

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from role_permission where PermissionId=#{PermissionId})")
    @Results({@Result(id = true,property = "id",column = "id"),
              @Result(property = "roleName",column = "roleName"),
              @Result(property = "roleDesc",column = "roleDesc"),})
    public List<Role> findRoleByPermissionId(String PermissionId) throws Exception;

    @Insert("insert into role(id, roleName, roleDesc) VALUES (replace(uuid(),'-',''),#{roleName},#{roleDesc})")
    void save(Role role);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com" +
                    ".rea.tours.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

}
