package com.example.demo.multithread.day2;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-12-18 16:01
 **/
public class SynchronizedDemo {
    public static void main(String[] args) {
        SynchronizedDemoTest test = new SynchronizedDemoTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.doTest();
            }
        });
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.doStop();
            }
        });
        t2.start();
        System.out.println("已经发起停止指定!");
    }
}

class SynchronizedDemoTest{
    private boolean flag = true;
    public void doTest(){
        while (flag){
            synchronized (this){

            }
        }
        System.out.println("执行停止!");
    }
    public void doStop(){
        flag = false;
    }
}
