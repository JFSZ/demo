package com.example.demo.设计模式.代理模式.test;

/**
 * @author chenxue
 * @Description
 * @Date 2019/9/6 16:14
 */

public class ProxyTest {
    public static void main(String[] args) {
        BeautyGirl mm = new BeautyGirl();
        mm.setName("李梅梅");
        ProxyPursuer proxyPursuer = new ProxyPursuer(mm);
        proxyPursuer.sendChocolate();
        proxyPursuer.sendDolls();
        proxyPursuer.sendFlower();
    }
}
