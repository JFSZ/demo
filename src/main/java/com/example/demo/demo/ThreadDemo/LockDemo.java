package com.example.demo.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: Lock 实现生产消费者模式
 * @author: cx
 * @create: 2020-03-29 22:53
 **/
public class LockDemo {
    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        for (int i = 0; i < 4; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    lockDemo.increment();
                }
            });
        }

        for (int i = 0; i < 4; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    lockDemo.decrement();
                }
            });
        }

    }
    //锁
    Lock lock = new ReentrantLock();
    //条件
    Condition condition = lock.newCondition();
    private final static Integer NUM = 10;
    private Integer current = 0;
    private List<Integer> list = new ArrayList<>();
    private boolean flag = true;
    /**
     * @Description: 生产者
     * @Author: chenxue
     * @Date: 2020/3/30  14:49
     */
    public void increment(){
            try{
                lock.lock();
                while(flag){
                    while( list.size() == NUM){
                        System.out.println("缓存池满了，等待中...");
                        condition.await();
                    }
                    list.add(current ++);
                    System.out.println("缓冲池中添加：" + current);
                    condition.signalAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
    }
    public void stop(){
        flag = false;
    }

    /**
     * @Description: 消费者
     * @Author: chenxue
     * @Date: 2020/3/30  14:50
     */
    public void decrement(){
            try {
                lock.lock();
                while(flag){
                    while(list.size() == 0){
                        System.out.println("缓冲池中空了，等待中。。。");
                        condition.await();
                    }
                    list.remove(current --);
                    System.out.println("缓冲池中移除:" + current);
                    condition.signalAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
    }

}
