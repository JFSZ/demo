package com.example.demo.springDemo.aop.proxy;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-05-05 11:03
 **/
public class StudentProxy implements PersonProxy {
    @Override
    public void doWork() {
        System.out.println("动态代理--doWork");
    }
}
