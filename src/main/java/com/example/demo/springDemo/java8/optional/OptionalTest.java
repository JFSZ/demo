package com.example.demo.springDemo.java8.optional;

import com.example.demo.model.User;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @description: Optional使用方法练习
 * Optional 是用来解决编程中常见的空指针问题。需要正确使用
 * 有两个创建方式: Optional.of(T t) 确定参数一定不为null时使用。如果为Null则报错
 * Optional.ofNullable(T t)  如果不确定参数是否为空则可以调用这个
 * @author: chenxue
 * @create: 2020-04-30 13:32
 **/
public class OptionalTest {
    public static void main(String[] args) throws Exception {
        Consumer c = System.out::println;
        List<String> list = Lists.newArrayList();
        Optional<String> optional = list.stream().findAny();
        // 对于没有用 optional.isPresent(); 直接调用optional.get()会报错  java.util.NoSuchElementException: No value present
        //正确使用方式:
        if(optional.isPresent())
            optional.get();
        //或者
        String s = optional.orElse("123");
        c.accept(s);
        // 弃用 if else 和以下写法相同
        User user = new User();
        user.setName("Tom");
        Optional.ofNullable(user).map(a -> a.getName()).orElseThrow(() -> new Exception("name is not null"));
        /*if(StringUtils.isBlank(user.getName())){
            throw new Exception("");
        }*/
        //:: 把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下，双冒号运算就是Java中的“方法引用”，
        Optional.ofNullable(user).filter(a -> StringUtils.isNotBlank(user.getName())).map(a -> a.getName().concat(" is Dog")).orElse("");
        //如果没有则，返回一个默认的对象 Supplier接口：为提供者的含义。
        User user1 = Optional.ofNullable(user).orElseGet(User::new);
        System.out.println(user1);



    }
}
