package com.rea.tours.aop;

import com.rea.tours.controller.SysLogController;
import com.rea.tours.domain.SysLog;
import com.rea.tours.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;


    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法

    @Before("execution(* com.rea.tours.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException
    {
        // 访问时间
        startTime=new Date();
        // 获取访问的类
        executionClass=jp.getTarget().getClass();
        // 获取访问的方法
        String methodName=jp.getSignature().getName();  // 获取访问的方法的名称

        // 获取访问的方法的参数
        Object[] args=jp.getArgs();
        // 无参数
        if(args==null||args.length==0){
            executionMethod=executionClass.getMethod(methodName);  // 只能获取无参数方法
        }else {
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs=new Class[args.length];
            for(int i=0;i<args.length;i++)
            {
                classArgs[i]=args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classArgs);  // 获取有参数方法
        }
    }

    //主要获取日志中其它信息，时长、ip、url...
    @After("execution(* com.rea.tours.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        // 获取类上的@RequestMapping对象
        if(executionClass!= SysLogController.class){
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                // 获取方法上的@RequestMapping对象
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null)
                {
                    // 它的值应该是类上的@RequestMapping的value+方法上的@RequestMapping的value
                    String url = classAnnotation.value()[0]+methodAnnotation.value()[0];

                    SysLog sysLog=new SysLog();
                    // 获取访问时长
                    Long executionTime=new Date().getTime()-startTime.getTime();
                    // 将sysLog对象属性封装
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUrl(url);
                    // 获取ip
                    String ip = request.getRemoteAddr();
                    sysLog.setIp(ip);

                    // 可以通过securityContext获取，也可以从request.getSession中获取
                    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username = principal.getUsername();
                    sysLog.setUsername(username);

                    //设入类和方法的信息
                    sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());
                    sysLog.setVisitTime(startTime);

                    // 调用Service，调用dao将sysLog insert数据库
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
