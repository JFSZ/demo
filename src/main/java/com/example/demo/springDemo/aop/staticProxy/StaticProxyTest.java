package com.example.demo.springDemo.aop.staticProxy;

/**
 * @description: 静态代理练习
 * @author: chenxue
 * @create: 2020-04-30 10:57
 **/
public class StaticProxyTest {

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

    }
}

//代理对象
class ProxySubject implements Subject{
    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void doWork() {
        this.doWork();
    }
}
