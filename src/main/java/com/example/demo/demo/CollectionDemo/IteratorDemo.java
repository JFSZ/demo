package com.example.demo.demo.CollectionDemo;

import java.util.*;

/**
 * @description: 迭代器demo 实现Iterator 接口的 都可以用迭代器遍历. foreach 底层使用的就是迭代器
 * 迭代器只能 单向遍历
 * 如果接口 实现了 RandomAccess
 * @author: chenxue
 * @create: 2020-03-26 15:20
 **/
public class IteratorDemo {
    public static void main(String[] args) {
        Collection collection = new LinkedList();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        Iterator iterator = collection.iterator();
        while(iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
