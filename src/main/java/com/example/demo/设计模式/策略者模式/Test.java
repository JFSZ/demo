package com.example.demo.设计模式.策略者模式;

/**
 * @author chenxue
 * @Description
 * @Date 2019/8/21 9:39
 */

public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        context.setAnimal(new Dog());
        context.eat();
        context.sleep();

        context.setAnimal(new Cat());
        context.eat();
        context.sleep();
    }
}
