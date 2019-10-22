package com.rea.tours.security;

import com.rea.tours.domain.Permission;
import com.rea.tours.service.IPermissionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;
/**
 * 自定义标签  用于前台判断按钮权限
 * @author A
 *
 */

public class AuthorizeTag extends BodyTagSupport
{
    private static final long SerialVersionUID = 1L;

    private String buttonUrl;

    public String getButtonUrl()
    {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl)
    {
        this.buttonUrl = buttonUrl;
    }

    @SuppressWarnings("static-access")
    @Override
    public int doStartTag() throws JspException
    {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//        //获取当前登录名
//        String name = securityContextImpl.getAuthentication().getName();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        //如果数据库里有该链接，并且该用户的权限拥有该权限，则显示，如果数据库没有该链接则不显示
        IPermissionService permissionService =(IPermissionService) SpringWiredBean.getInstance().getBeanById(
                "permissionService");
        List<Permission> permissions=permissionService.findAll();
        boolean flag=true;
        for (Permission p0 : permissions)
        {
            if (p0.getUrl().equals(buttonUrl))
                flag=false;
        }
        if (flag)
        {
            return EVAL_BODY_INCLUDE;
        }else
        {
//            Permission permission=new Permission();
//            permission.setUsername(name);
//            permission.setUrl(buttonUrl);
            List<Permission> permissionList = permissionService.loadPermission(username);
            //数据库中有该链接，并且该用户拥有该角色，显示
            for (Permission p1 : permissionList)
            {
                if (p1.getUrl().equals(buttonUrl))
                    return EVAL_BODY_INCLUDE;
            }
//            if (permissionList.size()>0)
//                return EVAL_BODY_INCLUDE;
        }
        //不显示
        return this.SKIP_BODY;
    }
}
