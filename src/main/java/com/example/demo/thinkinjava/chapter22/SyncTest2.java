package com.example.demo.thinkinjava.chapter22;

/**
 * @description: 锁重入练习 锁重入具有继承性 出现异常会释放锁
 * @author: chenxue
 * @create: 2019-12-12 16:03
 **/
public class SyncTest2 {
    public static void main(String[] args) {
        SyncTest21 s = new SyncTest21();
        //s.test();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                s.test3();
            }
        });
        t1.setName("a");
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                s.test3();
            }
        });
        t2.setName("b");
        t2.start();
    }
}

class SyncTest21{
    synchronized void test(){
        System.out.println("test");
        test1();
    }
    synchronized void test1(){
        System.out.println("test1");
        test2();
    }
    synchronized void test2(){
        System.out.println("test2");
    }
    synchronized void test3(){
        if(Thread.currentThread().getName().equalsIgnoreCase("a")){
            System.out.println("线程名称为 : " + Thread.currentThread().getName());
            Integer.parseInt("$");
        }else {
            System.out.println("线程名称为 : " + Thread.currentThread().getName());
        }
    }
}
