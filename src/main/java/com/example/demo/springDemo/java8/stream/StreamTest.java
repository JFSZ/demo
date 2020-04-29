package com.example.demo.springDemo.java8.stream;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @description: Stream 练习
 * @author: chenxue
 * @create: 2020-04-29 16:04
 **/
public class StreamTest {
    public static void main(String[] args) {
        Consumer c = System.out::println;
        //Stream 创建方法
        Stream stream = Stream.of("1","2","a","b");
        //filter 过滤器 符合要求才可以
        stream.filter(a -> a.equals("a")).forEach(b -> c.accept(b));
        //map 对Stream流中的每个元素进行 相应的操作
        Stream<String> stream1 = Stream.of("a","b","c");
        stream1.map(a -> a.concat(".txt")).forEach(b -> c.accept(b));
        //flatMap 对流元素中每个对象进行流处理。然后统一返回
        Stream<String> stream2 = Stream.of("a","b","c");
        stream2.flatMap(a -> Stream.of(a).map(b -> b.concat(".avg"))).forEach(b -> c.accept(b));
        //利用 Stream.generate()创建对象
        Stream<Double> stream3 = Stream.generate(() -> Math.random()*100).limit(10);
        //mapToInt 转换为Int
        stream3.mapToInt(a -> a.intValue()).forEach(b -> c.accept(b));

    }
}
