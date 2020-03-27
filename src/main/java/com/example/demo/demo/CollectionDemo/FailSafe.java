package com.example.demo.demo.CollectionDemo;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: java集合安全失败
 * 原理: 在循环时，copy 一个副本，在副本上进行迭代。并在迭代时加锁 ReentrantLock。并且是在写时加锁,读取时不加锁
 * 不会比较modCount 与expectedModCount 不会抛出异常 代表: CopyOnWriteArrayList、ConcurrentHashMap
 * 它如果进行了修改，运行不会抛出异常，读取的是老数据，允许安全失败一次，下次刷新或重启才会显示新数据
 * @author: chenxue
 * @create: 2020-03-26 14:56
 **/
public class FailSafe {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 6; i++) {
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
