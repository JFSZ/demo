package com.example.demo.设计模式.代理模式.静态代理;

/**
 * @author chenxue
 * @Description 静态代理
 * @Date 2019/8/21 16:14
 */

public class StaticProxy {
    public static void main(String[] args) {
        Employee employee = new NomalEmployee(new BossEmployee("王五"));
        employee.goWork("张三");
        employee.doWork();
    }
}
