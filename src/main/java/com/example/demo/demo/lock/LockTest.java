package com.example.demo.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 自定义锁测试类
 * @author: cx
 * @create: 2020-04-02 23:25
 **/
public class LockTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new Test());
        }
    }
}
class Test implements Runnable{
    @Override
    public void run() {
        MyLock1 myLock = new MyLock1();
        try {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() +" - 休眠前");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +" - 休眠后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            myLock.unlock();
        }
    }
}
