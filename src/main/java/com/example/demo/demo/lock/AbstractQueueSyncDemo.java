package com.example.demo.demo.lock;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @description: 手写同步器
 * AbstractQueuedSynchronizer 抽象的队列式的同步器
 * AQS定义了一套多线程访问共享资源的同步器框架，许多同步类实现都依赖于它，如常用的ReentrantLock/Semaphore/CountDownLatch...
 * @author: chenxue
 * @create: 2020-04-03 15:47
 **/
public class AbstractQueueSyncDemo implements Serializable {
    protected AbstractQueueSyncDemo(){}
    // 节点双向队列 FIFO，并且把当前线程包装为Node节点
    static class Node{
        //共享模式
        static final Node SHARED = new Node();
        //独占模式
        static final Node EXCLUSIVE = null;
        //前节点 多线程间可见
        volatile Node pred;
        //后节点
        volatile Node next;
        //节点从同步队列中取消
        static final int CANCELLED = -1;
        //后继节点的线程处于等待状态，如果当前节点释放同步状态会通知后继节点，使得后继节点的线程能够运行；
        static final int SIGN = -1;
        //当前节点进入等待队列中
        static final int CONDITION = -2;
        //表示下一次共享式同步状态获取将会无条件传播下去
        static final int PROPAGATE = -3;
        //节点状态
        volatile int waitState;
        //当前线程
        volatile Thread thread;
        Node nextWaiter;
        Node(){}

        Node(Thread thread,Node model){
            this.thread = thread;
            this.nextWaiter = model;
        }

        final Node predecessor(){
            Node p = pred;
            if(p == null){
                throw new NullPointerException();
            }else
                return p;
        }
    }
    //标识是否锁状态
    private volatile int state;
    //头结点
    private Node head;
    //尾节点
    private Node tail;

    /**
     * @Description: 统一获取 资源入口
     * @param
     * @Author: chenxue
     * @Date: 2020/4/3  16:13
     */
    public void acquire(int arg){
        //如果抢占到资源直接返回，否则就加入等待队列。如果抢占资源失败，单加入队列成功。则阻塞当前线程
        if(!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE),arg))
            selfInterrupt();
    }

    //排队等待获取锁
    protected boolean acquireQueued(Node node,int arg){
        //标识是否获取到锁
        boolean failed = false;
        try{
            //标识释放阻塞锁
            boolean interrpute = true;
            for (;;){
                Node p = node.predecessor();
                // 如果 当前线程前节点为head ，并且获取锁成功。则返回.
                if(p == head && tryAcquire(arg)){
                    setHead(node);
                    //释放前节点。让GC回收。
                    p.next = null;
                    failed = true;
                    return true;
                }
                if(shouldParkAfterFailedAcquire(p,node) && parkAndCheckInterrupt())
                    interrpute = true;
            }
        }finally {

        }
    }

    //加入等待队列。等待获取锁
    protected boolean shouldParkAfterFailedAcquire(Node pred,Node node){
        int w = pred.waitState;
        //如果前一个节点状态为 SIGN，则直接返回
        if(w ==  Node.SIGN){
            return true;
        }
        if(w > 0){
            //说明前一个节点取消,那么久无限循环找前一个节点不是取消的节点
            do {
                node.pred = pred = pred.pred;
            }while (pred.waitState > 0);
            pred.next = node;
        }else {

        }
    }
    // 加入等待队列中
    private Node addWaiter(final Node model){
        //新建一个节点
        Node node = new Node(Thread.currentThread(),model);
        Node n = tail;
        if(n != null){
            if(compareAndSetTail(n,node)){
                node.pred = n;
                n.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }

    //加入队列,快速加入队尾
    private Node enq(Node node){
        //进入这说明这是CHL等待队列 中第一个元素。但是这里考虑到多线程，有可能在加入队列时。另外一个线程已经在队列中添加一个元素。
        // 最后利用自旋保证肯定能加入到队列尾部
        for (;;){
            Node t = tail;
            if(t == null){
                if(compareAndSetHead(node)){
                    tail = head;
                }
            }else {
                node.pred = t;
                if(compareAndSetTail(t,node)){
                    t.next = node;
                    return t;
                }
            }
        }
    }

    //模板方法,需要让具体实现类实现
    public boolean tryAcquire(int arg){
        throw new UnsupportedOperationException();
    }

    //阻塞当前线程
    protected void selfInterrupt(){
        Thread.currentThread().interrupt();
    }

    public void setHead(Node head) {
        this.head = head;
    }

    private boolean compareAndSetTail(Node expect, Node update){
        return unsafe.compareAndSwapObject(this,tailOffset,expect,update);
    }

    private boolean compareAndSetHead(Node update){
        return unsafe.compareAndSwapObject(this,headOffset,null,update);
    }


    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;
    static {
        try {
            //找到 对应 内存中的位置
            stateOffset = unsafe.objectFieldOffset
                    (AbstractQueueSyncDemo.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (AbstractQueueSyncDemo.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (AbstractQueueSyncDemo.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (AbstractQueueSyncDemo.Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                    (AbstractQueueSyncDemo.Node.class.getDeclaredField("next"));

        } catch (Exception ex) { throw new Error(ex); }
    }
}
