package com.example.demo.thinkinjava.chapter22;

/**
 * @description: 线程的优先级具有继承性
 * @author: chenxue
 * @create: 2019-12-10 17:53
 **/
public class MyThread2 extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread2 线程优先级为:" + this.getPriority());
        MyThread21 myThread21 = new MyThread21();
        myThread21.start();
    }
}
class MyThread21 extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread21 线程优先级为:" + this.getPriority());
    }
}
class MyThread2Test{
    public static void main(String[] args) {
        System.out.println("MyThread2Test 线程优先级为:" + Thread.currentThread().getPriority());
        //Thread.currentThread().setPriority(7);
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}
