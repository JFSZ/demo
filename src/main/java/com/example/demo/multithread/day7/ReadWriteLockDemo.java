package com.example.demo.multithread.day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁练习
 * @author: chenxue
 * @create: 2019-12-24 10:32
 **/
public class ReadWriteLockDemo {
    private Double b;

    public void setB(Double b) {
        this.b = b;
    }

    public Double getB() {
        return b;
    }

    public static void main(String[] args) {
        ReadWriteLockTest test = new ReadWriteLockTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.read();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.write();
            }
        });
        t1.start();
        t2.start();
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        Collections.reverse(list);
        System.out.println(list.toString());
    }
}

class ReadWriteLockTest{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read(){
        try {
            lock.readLock().lock();
            System.out.println("获取读锁：" + Thread.currentThread().getName() + "时间为:" + System.currentTimeMillis());
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
    public void write(){
        try {
            lock.writeLock().lock();
            System.out.println("获取写锁：" + Thread.currentThread().getName() + "时间为:" + System.currentTimeMillis());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
