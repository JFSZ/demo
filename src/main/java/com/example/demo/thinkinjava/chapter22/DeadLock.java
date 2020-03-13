package com.example.demo.thinkinjava.chapter22;

/**
 * @description: 死锁练习 线程之间互相之间形成闭环依赖。
 * @author: chenxue
 * @create: 2019-12-16 08:20
 **/
public class DeadLock {
    public static void main(String[] args) {
        DeadLockTest test = new DeadLockTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test();
            }
        });
        t1.setName("A");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test1();
            }
        });
        t2.setName("B");
        t2.start();
    }
}
class DeadLockTest {
    private final static String sync1 = "A";
    private final static String sync2 = "B";
     public void test(){
         synchronized(sync1){
             System.out.println("线程test");
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             synchronized (sync2){
                 test1();
             }
         }

    }

     public void test1(){
         synchronized(sync2){
             System.out.println("线程test1");
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             synchronized (sync1){
                 test();
             }
         }
    }
}
