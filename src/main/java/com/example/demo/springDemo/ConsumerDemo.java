package com.example.demo.springDemo;

import java.util.function.Consumer;

/**
 * @description: java8 函数式接口
 * @author: chenxue
 * @create: 2020-04-22 14:25
 **/
public class ConsumerDemo {
    public static void main(String[] args) {
        //@FunctionalInterface 函数式接口 标识该接口为函数式接口。可以使用Lambda 表达式表示该接口的一个实现类
        //如下：表示声明一个匿名实现类 consumer
        Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a);
        };
        consumer.accept(10);
        //链式调用
        consumer.andThen(consumer).accept(15);
    }
}
