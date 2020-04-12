package com.example.demo.demo.ThreadDemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义线程池 自定义等待队列、线程生产工厂、拒绝策略。
 * @author: chenxue
 * @create: 2020-04-01 14:50
 **/
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        //核心线程
        int corePoolSize = 3;
        //最大线程数
        int maximumPoolSize = 5;
        //线程空闲时间
        int keepAliveTime = 10;
        //时间单位
        TimeUnit timeUnit = TimeUnit.SECONDS;
        MyThreadFactory myThreadFactory = new MyThreadFactory();
        MyIgnorePolicy myIgnorePolicy = new MyIgnorePolicy();
        //等待队列
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                blockingQueue,
                myThreadFactory,
                myIgnorePolicy);
        for (int i = 0; i < 15; i++) {
            executor.execute(new MyTask(String.valueOf(i)));
            if(i >= 14){
                executor.shutdown();
            }
        }

    }
    /**
     * @Description:线程工厂
     * @Author: chenxue
     * @Date: 2020/4/1  15:36
     */
    static class MyThreadFactory implements ThreadFactory{
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r,"my-thread" + atomicInteger.getAndIncrement());
            System.out.println("create Thread" + t.getName());
            return t;
        }
    }

    /**
     * @Description: 拒绝策略
     * @Author: chenxue
     * @Date: 2020/4/1  15:40
     */
    static class MyIgnorePolicy extends ThreadPoolExecutor.AbortPolicy{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("refuse Thread"  + r.toString());
        }
    }

    static class MyTask implements Runnable{
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            //doSomething
            System.out.println(this.toString() + "is running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyTask [name" + name + "]";
        }
    }

}
