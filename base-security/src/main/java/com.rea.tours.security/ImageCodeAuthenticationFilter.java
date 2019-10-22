package com.rea.tours.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ImageCodeAuthenticationFilter extends OncePerRequestFilter
{

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

//    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler)
//    {
//        this.authenticationFailureHandler = authenticationFailureHandler;
//    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        //判断当前请求 是否为登录请求
        if(request.getRequestURI().contains("/login"))
        {
            //获取验证码
            try{
                //获取用户输入的验证码
                final String imageCode = request.getParameter("imageCode");

                //获取系统生成的验证码
                String key = (String)request.getSession().getAttribute("key");

                if(StringUtils.isEmpty(imageCode.trim())){
                    throw new ImageCodeException("验证码必须输入");
                }

                if(!imageCode.trim().equals(key.trim())){
                    throw new ImageCodeException("验证码不一致");
                }
            }catch (AuthenticationException e){
                //交给自定义的AuthenticationFailureHandler处理
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }

        }

        filterChain.doFilter(request,response);
    }
}
