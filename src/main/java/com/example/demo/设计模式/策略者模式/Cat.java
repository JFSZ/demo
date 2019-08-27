package com.example.demo.设计模式.策略者模式;

/**
 * @author chenxue
 * @Description TODO
 * @Date 2019/8/21 9:30
 */

public class Cat implements Animal{
    @Override
    public void eat() {
        System.out.println("Cat eat fish");
    }

    @Override
    public void sleep() {
        System.out.println("Cat sleep");
    }
}
