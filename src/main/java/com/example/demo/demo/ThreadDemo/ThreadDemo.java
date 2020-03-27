package com.example.demo.demo.ThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 线程demo
 * 并发三要素: 原子性 可见性 有序性
 * 出现线程安全的原因:
 * 线程切换带来原子性问题  缓存导致可见性 编译优化带来有序性
 * @author: chenxue
 * @create: 2020-03-27 09:41
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        //创建线程的方式: 1 Thread 2 Runnable  3 线程池 Excutor 4 CallAble Future CallAble 可以有返回值

        // 线程池方式
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //do something
            }
        });

        //线程 Thread 方式
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
        // Runnable 接口
        RunDemo runDemo = new RunDemo();
        Thread t1 = new Thread(runDemo);
        t1.start();
    }

    static class RunDemo implements Runnable{

        @Override
        public void run() {
            //do something
        }
    }

    //Callable 接口FutureTask 实现多线程


}
class MyCallAble implements Callable<String>{
    public volatile int count;
    @Override
    public String call() throws Exception {
        do{
            System.out.println(++ count);
        }while (count < 10);
        return count + "";
    }
}
