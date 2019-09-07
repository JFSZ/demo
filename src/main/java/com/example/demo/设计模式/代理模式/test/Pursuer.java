package com.example.demo.设计模式.代理模式.test;

/**
 * @author chenxue
 * @Description 具体追求者
 * @Date 2019/9/5 11:03
 */

public class Pursuer implements Man {
    private BeautyGirl girl;
    public Pursuer(BeautyGirl girl) {
        this.girl = girl;
    }

    @Override
    public void sendFlower() {
        System.out.println(girl.getName() + "具体追求者sendFlower");
    }

    @Override
    public void sendDolls() {
        System.out.println("具体追求者sendDolls");
    }

    @Override
    public void sendChocolate() {
        System.out.println("具体追求者sendChocolate");
    }
}
