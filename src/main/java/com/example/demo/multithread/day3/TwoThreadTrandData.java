package com.example.demo.multithread.day3;

/**
 * @description: 不使用 等待/通知 机制实现线程通信
 * @author: chenxue
 * @create: 2019-12-18 16:14
 **/
public class TwoThreadTrandData {
    public static void main(String[] args) {
        String lock = "A";
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               synchronized (lock){
                   System.out.println("wait 上面");
                   try {
                       lock.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("wait下面");
               }
            }
        });
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {


        }
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    lock.notify();
                }
            }
        });
        t2.start();
    }
}
