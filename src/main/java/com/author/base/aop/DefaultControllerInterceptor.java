package com.author.base.aop;

/**
 * Created by Administrator on 2016/8/8.
 */

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
public class DefaultControllerInterceptor {
    public DefaultControllerInterceptor() {
    }

    @Pointcut("execution(* com.author.system.controller.DefaultController.*(..))")
    public void findService() {
    }

    @Before(
            value = "findService() && @annotation(annotation)",
            argNames = "annotation"
    )
    public void afterInterceptor(RequestMapping annotation) throws Throwable {
        System.out.println("访问了：" + annotation.value());
    }
}

