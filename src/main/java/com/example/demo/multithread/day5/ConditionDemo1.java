package com.example.demo.multithread.day5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 唤醒指定线程
 * @author: chenxue
 * @create: 2019-12-21 16:44
 **/
public class ConditionDemo1 {
    public static void main(String[] args) {
        ConditionTest1 test1 = new ConditionTest1();
        Thread t1 = new Thread(() -> test1.waitA());
        t1.start();
        Thread t2 = new Thread(() -> test1.waitB());
        t2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1.signalA();
        test1.signalB();
    }
}
class ConditionTest1{
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    public void waitA(){
        try {
            lock.lock();
            System.out.println("waitA start");
            System.out.println("start total time is :" + System.currentTimeMillis());
            conditionA.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void waitB(){
        try {
            lock.lock();
            conditionB.await();
            System.out.println("waitB start");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void signalA(){
        try {
            lock.lock();
            conditionA.signal();
            System.out.println("waitA end");
            System.out.println("end total time is :" + System.currentTimeMillis());
        }finally {
            lock.unlock();
        }
    }
    public void signalB(){
        try {
            lock.lock();
            conditionB.signal();
            System.out.println("waitB end");
        }finally {
            lock.unlock();
        }
    }
}
