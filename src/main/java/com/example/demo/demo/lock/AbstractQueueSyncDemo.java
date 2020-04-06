package com.example.demo.demo.lock;

import sun.misc.Unsafe;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: 手写同步器 写在前面的话: head 表示同步队列（即每次如果线程抢到锁，就把它设置为head）,其他为等待队列
 * AbstractQueuedSynchronizer 抽象的队列式的同步器
 * AQS定义了一套多线程访问共享资源的同步器框架，许多同步类实现都依赖于它，如常用的ReentrantLock/Semaphore/CountDownLatch...
 * @author: chenxue
 * @create: 2020-04-03 15:47
 **/
public class AbstractQueueSyncDemo extends AbstractOwnableSynchronizer implements Serializable{
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
        //节点从同步队列中取消 当timeout或被中断（响应中断的情况下），会触发变更为此状态，进入该状态后的结点将不会再变化
        static final int CANCELLED = 1;
        //后继节点的线程处于等待状态，如果当前节点释放同步状态会通知后继节点，使得后继节点的线程能够运行；
        static final int SIGN = -1;
        //表示结点等待在Condition上，当其他线程调用了Condition的signal()方法后，CONDITION状态的结点将从等待队列转移到同步队列中，等待获取同步锁。
        static final int CONDITION = -2;
        //共享模式下，前继结点不仅会唤醒其后继结点，同时也可能会唤醒后继的后继结点。
        static final int PROPAGATE = -3;
        //节点状态 这里是Doug Lea 精心设计的状态 当 waitState > 1 则表示当前线程已被取消
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

    protected final int getState() {
        return state;
    }

    protected final void setState(int state) {
        this.state = state;
    }

    /**
     * @Description: 独占模式：统一获取锁入口
     * @param
     * @Author: chenxue
     * @Date: 2020/4/3  16:13
     */
    public final void acquire(int arg){
        //如果抢占到资源直接返回，否则就加入等待队列。如果抢占资源失败，单加入队列成功。则阻塞当前线程。在此期间是不响应线程中断的。只有在中断线程获取锁之后，处理中断线程
        if(!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE),arg))
            selfInterrupt();
    }

    /**
     * @Description: 独占模式:释放锁。
     * @param arg
     * @Author: chenxue
     * @Date: 2020/4/6  13:54
     */
    public final boolean release(int arg){
        if(tryRelease(arg)){
            Node h =head;
            if(h != null && h.waitState != 0){
                unparkSuccessor(h);
                return true;
            }
        }
        return false;
    }

    //排队等待获取锁 使线程阻塞在等待队列中获取锁，直到获取到锁或被中断 返回true ,否则返回false
    protected boolean acquireQueued(Node node,int arg){
        //标识是否获取到锁
        boolean failed = true;
        try{
            //标识是否阻塞
            boolean interrpute = false;
            for (;;){
                Node p = node.predecessor();
                // 如果 当前线程前节点为head ，并且获取锁成功。则返回.
                if(p == head && tryAcquire(arg)){
                    setHead(node);
                    //释放前节点。让GC回收。
                    p.next = null;
                    failed = false;
                    return interrpute;
                }
                //如果在线程等待期间，该线程中断过，哪怕一次也标记为true
                if(shouldParkAfterFailedAcquire(p,node) && parkAndCheckInterrupt())
                    interrpute = true;
            }
        }finally {
            //如果当前线程被阻塞，则不会走到这里
            if(failed)
                //走到这里说明，线程被中断或者异常。则在等待队列中标记当前线程为取消
                cancelAcquire(node);
        }
    }

    /**
     * @Description: 模板方法：需要子类重新该方法。
     * @param arg
     * @Author: chenxue
     * @Date: 2020/4/6  13:58
     */
    protected boolean tryRelease(int arg){
        throw new UnsupportedOperationException();
    }

    //加入等待队列。等待获取锁
    protected boolean shouldParkAfterFailedAcquire(Node pred,Node node){
        int w = pred.waitState;
        //如果前一个节点状态为 SIGN，则直接返回
        if(w ==  Node.SIGN){
            return true;
        }
        if(w > 0){
            //说明前一个节点取消,那么久无限循环找前一个没有取消的节点
            do {
                node.pred = pred = pred.pred;
            }while (pred.waitState > 0);
            pred.next = node;
        }else {
            // 到这说明前节点状态正常,只需要把前节点设置为SIGN，通知后置节点即可。
            compareAndSetWaitState(pred,w,Node.SIGN);
        }
        return false;
    }

    /**
     * @Description: 如果前置节点状态已经设置为SIGN,那么后节点就可以休息。即否阻塞队列
     * @param
     * @Author: chenxue
     * @Date: 2020/4/6  9:32
     */
    protected boolean parkAndCheckInterrupt(){
        //调用park()方法，让当前线程进入waiting状态
        LockSupport.park(this);
        //如果被唤醒，则检查自己是否是被中断，还是正常唤醒
        return Thread.interrupted();
    }

    /**
     * @Description: 设置当前线程为取消，并标记为null，通知GC回收。并且让队列中的前节点和它的后节点相连
     * @param
     * @Author: chenxue
     * @Date: 2020/4/6  9:51
     */
    private void cancelAcquire(Node node){
        //如果为空，则不管
        if(null == node)
            return;
        node.thread = null;
        Node pred = node.pred;
        //忽略状态为取消的前置节点
        while (pred.waitState > 0)
            node.pred = pred = pred.pred;
        Node preNext = pred.next;
        node.waitState = Node.CANCELLED;
        //如果当前节点是尾节点,则把它的前置节点设置为尾节点
        if(tail == node && compareAndSetTail(node,pred)){
            compareAndSetNext(pred,preNext,null);
        }else {
            int ws;
            if(pred != head
                    && ((ws = pred.waitState) == Node.SIGN || (ws <= 0 && compareAndSetWaitState(pred,ws,Node.SIGN)))
                    && pred.thread != null){
                Node next = node.next;
                if(next != null && next.waitState <= 0){
                    compareAndSetNext(pred,preNext,next);
                }
            }else {
                //直接唤醒下个节点的线程
                unparkSuccessor(node);
            }
        }
    }


    // 加入等待队列尾部，并标记为独占模式
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
    protected boolean tryAcquire(int arg){
        throw new UnsupportedOperationException();
    }

    /**
     * @Description: 唤醒下个节点的线程，让下个节点开始竞争锁
     * @param node
     * @Author: chenxue 
     * @Date: 2020/4/6  11:05
     */ 
    private void unparkSuccessor(Node node){
        int ws = node.waitState;
        if(ws < 0)
            compareAndSetWaitState(node,ws,0);
        Node next = node.next;
        if(next == null || next.waitState > 0){
            next = null;
            for (Node i = tail; i != null && i != next ; i = i.pred) {
                if(i.waitState <= 0){
                    next = i;
                }
            }
        }
        if (next != null){
            LockSupport.unpark(next.thread);
        }

    }
    

    //阻塞当前线程,把当前线程标识为阻塞
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

    private boolean compareAndSetWaitState(Node node,int expect,int update){
        // 找到内存中需要改变的对象node 位置，然后根据内存偏移量 waitStatusOffset，如果waitState 和 expect 一样，则更新为update
        return unsafe.compareAndSwapInt(node,waitStateOffset,expect,update);
    }

    private boolean compareAndSetNext(Node node,Node expect,Node update){
        return unsafe.compareAndSwapObject(node,nextOffset,expect,update);
    }

    protected boolean compareAndSetState(int except,int update){
        return unsafe.compareAndSwapInt(this,stateOffset,except,update);
    }


    private static final Unsafe unsafe = getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStateOffset;
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
            waitStateOffset = unsafe.objectFieldOffset
                    (AbstractQueueSyncDemo.Node.class.getDeclaredField("waitState"));
            nextOffset = unsafe.objectFieldOffset
                    (AbstractQueueSyncDemo.Node.class.getDeclaredField("next"));

        } catch (Exception ex) { throw new Error(ex); }
    }

    // JDK 不允许直接访问 Unsafe.getUnsafe.可以使用下列方法访问
    public static Unsafe getUnsafe() {
        try{
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return(Unsafe)f.get(null);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
