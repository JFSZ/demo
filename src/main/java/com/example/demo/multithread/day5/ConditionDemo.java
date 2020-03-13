package com.example.demo.multithread.day5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: Lock 中 Condition 等待/通知 练习
 * @author: chenxue
 * @create: 2019-12-21 16:03
 **/
public class ConditionDemo {
    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test.waitMethod();
            }
        });
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.sign();
    }
}
class ConditionTest{
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void waitMethod(){
        try {
            lock.lock();
            System.out.println("执行等待。。 "+ System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void sign(){
        try {
            lock.lock();
            System.out.println("结束等待。。 "+ System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
