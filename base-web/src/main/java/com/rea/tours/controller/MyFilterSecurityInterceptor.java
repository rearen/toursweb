package com.rea.tours.controller;

import com.rea.tours.service.impl.MyAccessDecisionManager;
import com.rea.tours.service.impl.MyFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.FilterInvocation;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter
{
    @Autowired
    private MyFilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    private MyAccessDecisionManager accessDecisionManager;

//    @Resource
//    private AuthenticationManager authenticationManager;

//    @Resource
//    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }


    @PostConstruct
    public void init() throws Exception{
//        super.setAccessDecisionManager(accessDecisionManager);
//        super.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource()
    {
        return this.securityMetadataSource;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    /**
     * @param fi 里面有一个被拦截的url,调用MyFilterInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限,
     *           再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     * @throws IOException
     * @throws ServletException
     */
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url,object为FilterInvocation对象
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
//        1.获取请求资源的权限
        //执行Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object);
        //2.是否拥有权限
        //this.accessDecisionManager.decide(authenticated, object, attributes);
//		System.err.println(" ---------------  MySecurityFilter invoke--------------- ");
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            // 执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public Class<? extends Object> getSecureObjectClass()
    {
        //下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误
        return FilterInvocation.class;
    }

    public MyFilterInvocationSecurityMetadataSource getSecurityMetadataSource() {

        return this.securityMetadataSource;
    }
//
    public void setSecurityMetadataSource(MyFilterInvocationSecurityMetadataSource newSource)
    {
        this.securityMetadataSource = newSource;
    }

    @Override
    public void destroy()
    {

    }

}
