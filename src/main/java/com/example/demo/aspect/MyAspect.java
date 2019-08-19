package com.example.demo.aspect;

import com.example.demo.annotation.Log;
import com.example.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author chenxue
 * @Description aspect切面编程 切面
 * @Date 2019/8/14 10:51
 */

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Autowired
    private LogService service;

    /**
     * @Description  切入点
     * @param
     * @author chenxue
     * @date 2019/8/14
    */
    @Pointcut("execution(public * com.example.demo.web..*.*(..))")
    public void controllerLog(){};

    @Pointcut("@annotation(com.example.demo.annotation.Log)")
    public void annotationLog(){};

    /**
     * @Description  前置通知，表示会在切入点之前执行。目前是打印一些信息。比如ip、method、url
     * @param joinPoint
     * @author chenxue
     * @date 2019/8/14
    */
    @Before("annotationLog()")
    public void logBeforeController(JoinPoint joinPoint){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)attributes).getRequest();
        System.out.println("*******************请求ip地址为:" + request.getRemoteAddr());
        System.out.println("*******************请求URL为:" + request.getRequestURI());
        System.out.println("*******************请求Method为:" + request.getMethod());
    }

    @Around("annotationLog()")
    public Object logAroundController(ProceedingJoinPoint pj){
        MethodSignature methodSignature = (MethodSignature) pj.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        System.out.println("==============切入点执行时，注解为" + log.methodName() + "；；；" + log.operateName());
        Object o = null;
        try {
            //让代理方法执行
            o = pj.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }

    @After("annotationLog()")
    public void logAfterController(JoinPoint joinPoint){
        Class cls = joinPoint.getSignature().getDeclaringType();

        System.out.println();
    }

    /** 
     * @Description 返回通知
     * @param joinPoint
     * @param result
     * @author chenxue
     * @date 2019/8/16
    */ 
    @AfterReturning(value = "annotationLog()",returning = "result")
    public Object logAfterReturnController(JoinPoint joinPoint,Object result){
        System.out.println("*******************返回值为：" + result);
        return result;
    }



}
