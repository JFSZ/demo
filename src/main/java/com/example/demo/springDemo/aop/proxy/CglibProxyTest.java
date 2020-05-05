package com.example.demo.springDemo.aop.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:  Cglib 动态代理
 * @author: chenxue
 * @create: 2020-05-05 17:41
 **/
public class CglibProxyTest implements MethodInterceptor {
    private Object targetObject;
    public static void main(String[] args) {
        CglibProxyTest test = new CglibProxyTest();
        StudentProxy instance = (StudentProxy)test.getInstance(new StudentProxy());
        instance.doWork();
    }


    public Object getInstance(Object object){
        this.targetObject = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    /**
     * @Description: 
     * @param o cglib生成的代理对象
     * @param method 被代理对象方法
     * @param objects 方法入参
     * @param methodProxy 代理方法
     * @Author: chenxue 
     * @Date: 2020/5/5  17:59
     */ 
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 代理前");
        Object invoke = methodProxy.invoke(targetObject, objects);
        System.out.println("cglib 代理后");
        return invoke;
    }
}
