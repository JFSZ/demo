package com.example.demo.demo.CollectionDemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 集合快速失败demo  继承 AbstractList 在失败时抛出异常，比较 modCount 与expectedModCount 代表:list ArrayList HashMap HashSet LinkedList等
 * 原理:集合在迭代时会检查集合的更改次数modCount字段 以及expectedModCount。如果不相等则就抛出 java.util.ConcurrentModificationException异常
 * @author: chenxue
 * @create: 2020-03-26 13:56
 **/
public class FastFail {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i + "");
        }
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String str : list){
                    try {
                        Thread.sleep(1000);
                        System.out.println("循环:" + str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    list.remove(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }
}
