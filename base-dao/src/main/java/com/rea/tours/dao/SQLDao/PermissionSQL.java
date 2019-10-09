package com.rea.tours.dao.SQLDao;

import com.rea.tours.domain.Permission;
import org.apache.ibatis.jdbc.SQL;

public class PermissionSQL {
    public String findPermissionByUserId(Permission permission){
        return new SQL(){
            {
                SELECT("*");
                FROM("permission` p,`role_permission` rp,`users_role` ur,`users` u");
//              WHERE("p.id=rp.permissionId AND rp.roleId=ur.roleId AND ur.userId=u.id");
                if(permission.getUsername()!=null){
                    WHERE("u.username=#{permission.getUsername()}");
                }
            }
        }.toString();
    }
}
