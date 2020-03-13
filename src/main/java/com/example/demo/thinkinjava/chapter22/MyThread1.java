package com.example.demo.thinkinjava.chapter22;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-12-10 16:18
 **/
public class MyThread1 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if(this.isInterrupted()){
                System.out.println("停止线程!");
                break;
            }
            System.out.println("i = " + i);
        }
    }
}
class test1{
    public static void main(String[] args) throws InterruptedException {
        MyThread1 thread1 = new MyThread1();
        thread1.start();
        Thread.sleep(2000);
        thread1.interrupt();
    }
}
