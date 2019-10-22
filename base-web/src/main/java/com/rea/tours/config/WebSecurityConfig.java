package com.rea.tours.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/login.jsp","/index.jsp"
                ,"/failer.jsp","/403.jsp","/css/**","/img/**","/plugins/**");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().loginPage("/login.jsp")
                .loginProcessingUrl("/login").defaultSuccessUrl("/pages/main.jsp")
                .failureUrl("/failer.jsp")
                .and()
                .logout().invalidateHttpSession(true)
                .logoutUrl("/logout").logoutSuccessUrl("/login.jsp")
                .and()
                .
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
