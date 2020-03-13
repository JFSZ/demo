package com.example.demo.thinkinjava.chapter22;

/**
 * @description: 字符串同步 synchronized(String) 注意事项 容易造成多个线程竞争同一个锁。导致阻塞现象
 * @author: chenxue
 * @create: 2019-12-13 16:54
 **/
public class StringSync {
    public static void main(String[] args) {
        StringSyncTest test = new StringSyncTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.doTest(new Object());
            }
        });
        t1.setName("A");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.doTest(new Object());
            }
        });
        t2.setName("B");
        t2.start();
    }
}
class StringSyncTest{
    public void doTest(Object param){
        synchronized(param){
            while (true){
                System.out.println("当前线程名称为：" + Thread.currentThread().getName() );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
