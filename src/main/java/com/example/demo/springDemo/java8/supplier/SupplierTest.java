package com.example.demo.springDemo.java8.supplier;

import com.example.demo.model.User;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @description: Supplier接口练习:
 *两种写法:双冒号函数:默认调用对象的默认构造器
 *      （）->｛｝ 方法：可以调用自定义函数
 * 所以每次get() 返回的对象是否相等。需要看具体如何实现。
 * @author: chenxue
 * @create: 2020-04-30 15:29
 **/
public class SupplierTest {


    public static void main(String[] args) {
        Consumer c = System.out::println;
        //两种写法: 双冒号函数: 默认调用对象的默认构造器
        //（）->｛｝ 方法：可以调用自定义函数
        // Supplier<User> supplier = User::new;
        Supplier<User> supplier = () -> User.getInstance();
        Lists.newArrayList(1,2,3).stream().forEach(a -> {
            c.accept(supplier.get().toString());
        });
        Supplier<LocalDateTime> supplier1 = () -> LocalDateTime.now();
        c.accept(supplier1);

    }
}
