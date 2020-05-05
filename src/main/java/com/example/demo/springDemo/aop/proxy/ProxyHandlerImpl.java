package com.example.demo.springDemo.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description: InvocationHandler接口是proxy代理实例的调用处理程序实现的一个接口，
 * 每一个proxy代理实例都有一个关联的调用处理程序；在代理实例调用方法时，方法调用被编码分派到调用处理程序的invoke方法。
 * @author: chenxue
 * @create: 2020-05-05 10:44
 **/
public class ProxyHandlerImpl<T> implements InvocationHandler {
    // 具体业务实现类
    private T target;

    public ProxyHandlerImpl(T target) {
        this.target = target;
    }

    /**
     * @Description: 
     * @param proxy 动态代理生成的 $Proxy0 类
     * @param method
     * @param args
     * @Author: chenxue 
     * @Date: 2020/5/5  17:35
     */ 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理--前");
        Object invoke = method.invoke(target, args);
        System.out.println("动态代理--后");
        return invoke;
    }
}
