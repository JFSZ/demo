package com.example.demo.springDemo.java8.consumer;

import com.example.demo.springDemo.annotation.aliasForDemo.MyAnnotationTest;

import java.util.function.Consumer;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-04-29 11:06
 **/
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer c = System.out::println;
        //语法为——方法的隶属者::方法名。有几个注意事项:如果直接用对象名::方法 那么需要方法未静态方法，并且要有参数
        Consumer consumer = MyAnnotationTest::doWork;
        c.accept("a");

    }
}
