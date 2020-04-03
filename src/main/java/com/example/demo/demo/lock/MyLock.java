package com.example.demo.demo.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义同步锁
 * 1、需要一个变量记录目前是哪个线程持有锁
 * 2、需要一个变量 记录是否阻塞。并且需要在多个线程见可见。
 * 3、获取锁/释放锁方法
 * 一：实现基本锁功能
 * 二：实现公平锁与非公平锁
 * 三：condition 条件
 * @author: cx
 * @create: 2020-04-02 21:14
 **/
public class MyLock {
    private Thread thread;
    //0 无锁 1 有锁
    private volatile AtomicInteger state = new AtomicInteger(0);
    private final Integer ZERO = 0;
    private final int ONE = 1;
    //头部节点
    private Node head;
    //尾部节点
    private Node tail;

    class Node{
        private Node prev;
        private Node next;
        private int waitState;
        private Thread thread;
        private Node nextWaiter;
        public Node(){}
        public Node(Thread thread,Node mode){
            this.thread = thread;
            this.nextWaiter = mode;
        }

    }
    /**
     * @Description: 统一获取资源 入口
     * @param
     * @Author: chenxue
     * @Date: 2020/4/3  15:46
     */
    public final void acquire(){

    }

    /**
     * @Author: cx
     * @Description:获取锁
     * @Date: 2020/04/02 22:25
     * @param
     * @return: boolean
     **/
    public boolean lock(){

        //自旋
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
        state.set(0);
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}


