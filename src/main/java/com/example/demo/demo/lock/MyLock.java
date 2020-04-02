package com.example.demo.demo.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义同步锁
 * 1、需要一个变量记录目前是哪个线程持有锁
 * 2、需要一个变量 记录是否阻塞。并且需要在多个线程见可见。
 * 3、获取锁/释放锁方法
 * @author: cx
 * @create: 2020-04-02 21:14
 **/
public class MyLock {
    private Thread thread;
    //0 无锁 1 有锁
    private volatile AtomicInteger state = new AtomicInteger(0);
    private final Integer ZERO = 0;
    private final int ONE = 1;

    /**
     * @Author: cx
     * @Description:获取锁
     * @Date: 2020/04/02 22:25
     * @param
     * @return: boolean
     **/
    public boolean lock(){
        //1、检查state 状态
        //2、自旋
        for (;;){
            //考虑重入锁
            if(getThread() == Thread.currentThread()){
                state.incrementAndGet();
                return true;
            }else if(state.compareAndSet(ZERO,ONE)){
                setThread(Thread.currentThread());
            }
        }
    }


    /**
     * @Author: cx
     * @Description: 释放锁
     * @Date: 2020/04/02 22:25
     * @param
     * @return: boolean
     **/
    public void unLock(){
        if(thread == null || thread != getThread())
            throw new RuntimeException("Thread Exception");
        setThread(null);
        state.decrementAndGet();
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}

class MyLock1{

    private Thread ownerThread;

    private volatile AtomicInteger state;


    public MyLock1(){
        state = new AtomicInteger(0);
    }

    public Thread getOwnerThread() {
        return ownerThread;
    }

    public void setOwnerThread(Thread ownerThread) {
        this.ownerThread = ownerThread;
    }

    public boolean lock(){
        //可重入
        for (;;) {
            if (Thread.currentThread() == getOwnerThread()){
                state.incrementAndGet();
                return true;
            }else if(state.compareAndSet(0,1)){
                setOwnerThread(Thread.currentThread());
            }
        }
    }

    public void unlock(){
        if (Thread.currentThread() != getOwnerThread()){
            throw new RuntimeException("不是锁持有线程，不能解锁");
        }
        setOwnerThread(null);
        state.decrementAndGet();
    }
}


