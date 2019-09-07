package com.example.demo.设计模式.代理模式.test;

/**
 * @author chenxue
 * @Description 代理类 代理追求者
 * @Date 2019/9/5 11:12
 */

public class ProxyPursuer implements Man{
    private Pursuer pursuer;

    public ProxyPursuer(BeautyGirl mm) {
        pursuer = new Pursuer(mm);
    }

    @Override
    public void sendFlower() {
        System.out.println("代理类--sendFlower--之前");
        pursuer.sendFlower();
        System.out.println("代理类--sendFlower--之后");
    }

    @Override
    public void sendDolls() {
        System.out.println("代理类--sendDolls--之前");
        pursuer.sendDolls();
        System.out.println("代理类--sendDolls--之后");
    }

    @Override
    public void sendChocolate() {
        System.out.println("代理类--sendChocolate--之前");
        pursuer.sendChocolate();
        System.out.println("代理类--sendChocolate--之后");
    }
}
