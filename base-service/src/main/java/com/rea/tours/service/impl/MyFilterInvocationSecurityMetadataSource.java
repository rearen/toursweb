package com.rea.tours.service.impl;

import com.rea.tours.dao.IPermissionDao;
import com.rea.tours.domain.Permission;
import com.rea.tours.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Transactional
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
    @Autowired
    private IPermissionDao permissionDao;

    private Map<String, List<ConfigAttribute>> urlPermMap=null;

    @PostConstruct
    public void init() {
        loadResourceDefine();
    }

    public void loadResourceDefine() {
        urlPermMap = new HashMap<>();
        List<Permission> permissions=permissionDao.findAll();
        for (Permission p:permissions)
        {
            List<ConfigAttribute> authorityList=null;
            for(Role r:p.getRoles()){
                ConfigAttribute auth = new SecurityConfig(r.getRoleName());
                authorityList.add(auth);
            }
            urlPermMap.put(p.getUrl(), authorityList);
        }

    }

    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException
    {
        String url = ((FilterInvocation) o).getRequestUrl();
        Set<String> keys = urlPermMap.keySet();
        for (String k : keys) {
            if (url.indexOf(k) >= 0) {
                return urlPermMap.get(k);
            }
        }
        return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }

    public boolean supports(Class<?> aClass)
    {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
