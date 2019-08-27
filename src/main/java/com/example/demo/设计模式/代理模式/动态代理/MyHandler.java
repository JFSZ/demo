package com.example.demo.设计模式.代理模式.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenxue
 * @Description 动态代理
 * @Date 2019/8/21 18:25
 */

public class MyHandler implements InvocationHandler {
    private Object object;
    public MyHandler(Object object){
        this.object = object;
    }
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前，做的事情。。。");
        Object result = method.invoke(object,args);
        System.out.println("代理后，做的事情。。。");
        return result;
    }
}
