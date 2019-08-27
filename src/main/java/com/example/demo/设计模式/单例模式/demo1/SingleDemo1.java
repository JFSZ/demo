package com.example.demo.设计模式.单例模式.demo1;

/**
 * @author chenxue
 * @Description 单例模式 饿汉模式
 * @Date 2019/8/21 11:57
 */

public class SingleDemo1 {
    private static SingleDemo1 singleDemo1 = new SingleDemo1();
    private SingleDemo1(){};
    public static SingleDemo1 getInstance(){
        return singleDemo1;
    }
}
