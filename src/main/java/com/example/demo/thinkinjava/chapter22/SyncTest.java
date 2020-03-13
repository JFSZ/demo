package com.example.demo.thinkinjava.chapter22;

/**
 * @description: 多个线程持有同一对象锁，访问多个加锁方法。需要排队 普通方法锁当前实例对象 静态方法是锁Class 同步块 锁括号内对象
 * 多线程 可以异步调用 非同步方法
 * @author: chenxue
 * @create: 2019-12-12 13:48
 **/
public class SyncTest {
    public static void main(String[] args) {
        SyncTest1 s1 = new SyncTest1();
        SyncTest1 s2 = new SyncTest1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                /*try {
                    s1.test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                s1.test2();
            }
        });
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               /* s1.test1();*/
                s2.test3();
            }
        });
        t2.setName("t2");
        t2.start();
    }
}
class SyncTest1{
    synchronized public void test() throws InterruptedException {
        System.out.println("test -- start:线程名称：" + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println(" test -- end");
    }
    synchronized public void test1(){
        System.out.println("test1-线程名称" + Thread.currentThread().getName());
    }
    synchronized static void test2(){
        System.out.println("test2 -- start:线程名称：" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2 -- end");
    }
    synchronized static void test3(){
        System.out.println("test3-线程名称" + Thread.currentThread().getName());
    }
}
