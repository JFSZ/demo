package com.example.demo.设计模式.代理模式.动态代理;

/**
 * @author chenxue
 * @Description 动态代理测试类
 * @Date 2019/8/22 12:44
 */

public class ProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",true);

        Subject subject = new MyHandler(new RealSubject()).getProxy();
        subject.doSomething();
    }
}
