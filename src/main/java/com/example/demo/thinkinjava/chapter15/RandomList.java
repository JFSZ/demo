package com.example.demo.thinkinjava.chapter15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 用于存储 各种类型的列表,有一个select() 方法。会随机选取元素
 * @author: chenxue
 * @create: 2019-10-12 16:23
 **/
public class RandomList<T> {
    //多线程下使用，为每个线程提供流单独的seek .避免多线程同时竞争，提高效率
    ThreadLocalRandom random = ThreadLocalRandom.current();
    private List<T> list = new ArrayList<>();
    // 添加元素
    public void add(T item){
        list.add(item);
    }
    //随机返回T
    public T select(){
        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomList<String> list = new RandomList<>();
        String str = "hello world ni hao ya lei hao ya ";
        Arrays.asList(str.split(" ")).stream().forEach(s -> {
            list.add(s);
        });
        System.out.println(list.select());
    }
}
