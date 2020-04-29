package com.example.demo.springDemo.java8.function;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @description: Function 函数练习
 * @author: chenxue
 * @create: 2020-04-29 13:36
 **/
public class FunctionTest {
    public static void main(String[] args) {
        Consumer c = System.out :: println;
        Function<Integer,Integer> f = a -> a+a;
        Function<Integer,Integer> f2 = a -> a * a;
        //compose 先执行传入逻辑，在执行本身逻辑
        Integer apply = f.compose(f2).apply(2);
        //andThen 先执行本身逻辑，在执行传入逻辑
        Integer apply1 = f.andThen(f2).apply(2);
        c.accept(apply);
        c.accept(apply1);
        Object apply2 = Function.identity().apply(1);
        c.accept(apply2);
    }
}
