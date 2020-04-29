package com.example.demo.springDemo.java8.predicate;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @description: Predicate练习 判断某个东西是否满足某种条件
 * @author: chenxue
 * @create: 2020-04-29 15:14
 **/
public class PredicateTest {
    public static void main(String[] args) {
        Consumer c = System.out::println;
        Predicate p = a -> a.equals("a");
        boolean a = p.test("a");
        c.accept(a);
        boolean a1 = p.negate().test("a");
        c.accept(a1);

    }
}
