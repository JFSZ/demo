package com.example.demo.设计模式.策略者模式;

/**
 * @author chenxue
 * @Description 动物类
 * 策略者模式: 把算法(行为)单独抽离，让对象可以在运行时更改行为
 *
 * @Date 2019/8/21 8:41
 */

public interface Animal {
    public void eat();
    public void sleep();
}
