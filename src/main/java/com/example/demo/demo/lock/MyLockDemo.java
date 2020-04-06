package com.example.demo.demo.lock;

/**
 * @description: 自定义Lock，独占锁
 * @author: chenxue
 * @create: 2020-04-06 14:23
 **/
public class MyLockDemo {

    private final Sync sync;

    // 内部类实现 AQS
    abstract static class Sync extends AbstractQueueSyncDemo{
        abstract void lock();

        /**
         * @Description: 具体实现上锁逻辑
         * @param arg
         * @Author: chenxue
         * @Date: 2020/4/6  15:11
         */
        final boolean nonFairTryAcquire(int arg){
            Thread currentThread = Thread.currentThread();
            //考虑重入锁
            int s = getState();
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(currentThread);
                return true;
            }else if(currentThread == getExclusiveOwnerThread()){
                int count = arg + s;
                if(count < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(count);
                return true;
            }
            return false;
        }


        /**
         * @Description: 父类中的模板方法,再具体实现类中实现
         * @param arg
         * @Author: chenxue
         * @Date: 2020/4/6  14:58
         */
        @Override
        protected boolean tryRelease(int arg) {
            //释放锁 当前state值
            int c = getState() - arg;
           if( Thread.currentThread() != getExclusiveOwnerThread())
               throw new IllegalMonitorStateException();
           boolean free = false;
           if(c == 0){
               setExclusiveOwnerThread(null);
               free = true;
           }
           setState(c);
            return free;
        }
    }
    // 非公平锁实现
    static final class NonfairSync extends Sync{

        @Override
        void lock() {
            //如果是第一个获取锁
            if(compareAndSetState(0,1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                acquire(1);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return nonFairTryAcquire(arg);
        }
    }
    //公平锁
    static final class FairSync extends Sync{

        @Override
        void lock() {
            acquire(1);
        }

        @Override
        protected boolean tryAcquire(int arg) {

            return false;
        }
    }

    //无参构造 默认实现非公平锁
    public MyLockDemo() {
        sync = new NonfairSync();
    }

    //有参构造
    public MyLockDemo(boolean fair){
        sync = fair ? new FairSync() : new NonfairSync();
    }


    /**
     * @Description:  加锁
     * @param
     * @Author: chenxue
     * @Date: 2020/4/6  14:55
     */
    public void lock(){
        sync.lock();
    }

    /**
     * @Description: 释放锁
     * @param
     * @Author: chenxue
     * @Date: 2020/4/6  14:56
     */
    public void unLock(){
        sync.release(1);
    }

}
