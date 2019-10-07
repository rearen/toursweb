package com.rea.tours.dao;

import com.rea.tours.domain.Permission;
import com.rea.tours.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao
{
    @Insert("insert into permission(id, permissionName, url) VALUES (replace(uuid(),'-',''),#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission")
    @Results({@Result(id=true,property = "id",column = "id"),
              @Result(property = "permissionName",column = "permissionName"),
              @Result(property = "url",column = "url"),
              @Result(property = "roles",column = "id",javaType =java.util.List.class,
                      many = @Many(select = "com.rea.tours.dao.IRoleDao.findRoleByPermissionId"))

    })
    List<Permission> findAll();

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findPermissionById(String roleId);

//    //查询与role关联的所有的权限
//    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
//    public List<Permission> findPermissionByRoleId(String id) throws Exception;
}
