package com.example.demo.thinkinjava.chapter22;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-11-29 10:40
 **/
public class ThreadTest {
    public static void main(String[] args) {
        int i = 0;
        while(i< 100){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
            i++;
        }
    }

    public static void test(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程名称为:" + Thread.currentThread().getName());
                //
                for(int i = 0;i<3;i++){
                    Thread.yield();
                    return;
                }
            }
        });
        t1.start();

    }
}
