package com.rea.tours.domain;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public class Permission
{
    private String id;
    private String permissionName;
    private String url;
    private List<Role> roles;
    private String username; //用于传递username参数

    public Permission getPermission(){
        return this;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPermissionName()
    {
        return permissionName;
    }

    public void setPermissionName(String permissionName)
    {
        this.permissionName = permissionName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "Permission{" + "id='" + id + '\'' + ", permissionName='" + permissionName + '\'' + ", url='" + url + '\'' + ", roles=" + roles + '}';
    }
}
