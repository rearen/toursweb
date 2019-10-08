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

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 加载资源与权限的对应关系
 */

@Service
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
    @Autowired
    private IPermissionDao permissionDao;
//    @Autowired
//    private IPermissionService permissionService;

//    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    private Map<String, Collection<ConfigAttribute>> urlPermMap=null;



    public MyFilterInvocationSecurityMetadataSource(){
        loadResourceDefine();
    }

//    @PostConstruct
//    public void init() {
//        loadResourceDefine();
//    }
    /**
     * @PostConstruct是Java EE 5引入的注解，
     * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
     * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
     *
     * //加载所有资源与权限的关系
     */
    @PostConstruct
    public void loadResourceDefine() {
        if(urlPermMap==null){
            urlPermMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Permission> permissions=permissionDao.findAll();
            for (Permission p:permissions)
            {
                List<ConfigAttribute> authorityList=null;
                Collection<ConfigAttribute> configAttributes=new ArrayList<>();
//                // 通过permission表的资源名称来表示具体的权限 注意：必须"ROLE_"开头
//                ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + p.getPermissionName());
//                configAttributes.add(configAttribute);
//                urlPermMap.put(p.getUrl(), configAttributes);

//                用role表来参与逻辑计算
                for(Role r:p.getRoles()){
                    ConfigAttribute auth = new SecurityConfig("ROLE_" +r.getRoleName());
                    authorityList.add(auth);
                }
                urlPermMap.put(p.getUrl(), authorityList);
            }
        }
    }

    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException
    {

        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println("requestUrl is " + requestUrl);
        if(urlPermMap.size()==0)
        {
            loadResourceDefine();
        }
        System.err.println("urlPermMap.get(requestUrl); "+urlPermMap.get(requestUrl));
        if(requestUrl.indexOf("?")>-1){
            requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
        }
        return urlPermMap.get(requestUrl);

//        Set<String> keys = urlPermMap.keySet();
//        for (String k : keys) {
//            if (url.indexOf(k) >= 0) {
//                return urlPermMap.get(k);
//            }
//        }
    }

    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }

    public boolean supports(Class<?> aClass)
    {
        return true;
    }
}
