package com.example.demo.demo.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 并发工具类 一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行
 * 线程池 记得随手关闭。否则容易造成OOM
 * @author: chenxue
 * @create: 2020-04-06 18:06
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            //doWorking
            try {
                //模拟线程耗时操作
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
            countDownLatch.countDown();
        });
        executorService.shutdown();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.execute(() -> {
            //doWorking
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
            countDownLatch.countDown();
        });
        executorService1.shutdown();
        try {
            //让主线程等待其他线程完成
            System.out.println("主线程--等待其他线程完毕，才能执行");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程都执行完毕");
    }
}
