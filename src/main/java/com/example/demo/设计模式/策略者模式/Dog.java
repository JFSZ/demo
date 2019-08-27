package com.example.demo.设计模式.策略者模式;

/**
 * @author chenxue
 * @Description
 * @Date 2019/8/21 9:29
 */

public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog eat bone");
    }

    @Override
    public void sleep() {
        System.out.println("Dog sleep");
    }
}
