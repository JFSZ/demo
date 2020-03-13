package com.example.demo.multithread.day6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: Lock 中公平锁练习 Lock分 公平锁和非公平锁。默认非公平锁。除非在 Lock lock = new ReentrantLock(true)
 * 传入true
 * lock.getHoldCount() 查询当前线程，保持此锁的个数
 * lock.getQueueLength() 返回当前正在等待 获取锁的线程个数
 * lock.getWaitQueueLength() 返回目前正在等待的 Condition 个数
 * @author: chenxue
 * @create: 2019-12-23 09:50
 **/
public class FairSyncDemo {
    public static void main(String[] args) {
        FairSyncTest test = new FairSyncTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    test.get();
                }
            });
            thread.start();
        }
    }
}
class FairSyncTest{
    public ReentrantLock lock = new ReentrantLock(true);
    public Condition condition = lock.newCondition();
    public void get(){
        try {
            lock.lock();
            System.out.println("this is thread's name is :" + Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println(lock.getHoldCount());
            System.out.println(lock.getQueueLength());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
