package com.example.demo.springDemo.aop.staticProxy;

/**
 * @description: 静态代理练习
 * @author: chenxue
 * @create: 2020-04-30 10:57
 **/
public class StaticProxyTest {
    public static void main(String[] args) {
        ProxySubject proxy = new ProxySubject();
        proxy.doWork();
    }
}

//抽象接口
@FunctionalInterface
interface Subject{
    void doWork();
}

//真实对象
class RealSubject implements Subject{

    @Override
    public void doWork() {
        System.out.println("realSubject doWork...");
    }
}

//代理对象
class ProxySubject implements Subject{
    private RealSubject realSubject = new RealSubject();

    public ProxySubject() {
    }

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void doWork() {
        System.out.println("ProxySubject doWork before");
        realSubject.doWork();
        System.out.println("ProxySubject doWork after");
    }
}
