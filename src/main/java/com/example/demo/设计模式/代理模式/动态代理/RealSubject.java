package com.example.demo.设计模式.代理模式.动态代理;

/**
 * @author chenxue
 * @Description TODO
 * @Date 2019/8/22 12:41
 */

public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("RealSubject : 具体实现类做事");
    }
}
