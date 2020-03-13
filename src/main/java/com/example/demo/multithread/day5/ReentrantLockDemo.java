package com.example.demo.multithread.day5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock 练习
 * @author: chenxue
 * @create: 2019-12-21 15:00
 **/
public class ReentrantLockDemo {
    public static void main(String[] args) {
        RenntrantLockTest test = new RenntrantLockTest();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    test.testMethod();
                }
            });
            thread.start();
        }
    }
}

class RenntrantLockTest{
    private Lock lock = new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("this Thread name is " + Thread.currentThread().getName());
        }
        lock.unlock();
    }
}
