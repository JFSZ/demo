package com.example.demo.multithread.day1;

import lombok.Data;

/**
 * @description: volatitle 练习 保证变量线程之间的可见 volatitle 作为指令关键字，确保该条指令不会因为编译器的优化而忽略
 * 强制从公共内存中取变量。
 * volatitle解决了变量在多线程之间的可见性。synchronized 解决了多线程访问共享资源（临界资源）同步问题
 *
 * @author: chenxue
 * @create: 2019-12-16 15:37
 **/
public class VolatileTest {
    public static void main(String[] args) {
        VolatileTest1 test1 = new VolatileTest1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //
                try {
                    Thread.sleep(2000);
                    test1.setName("A - 测试");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(test1.getName());
            }
        });
        t1.setName("A");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test1.setName("B - 测试");
                System.out.println(test1.getName());
            }
        });
        t2.start();
    }
}

@Data
class VolatileTest1{
    volatile String name;
}