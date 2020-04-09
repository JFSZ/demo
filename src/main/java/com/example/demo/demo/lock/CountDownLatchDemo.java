package com.example.demo.demo.lock;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 并发工具类 一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行 并且在计数器到0后，不可复用,否则没有作用
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
                System.out.println("子线程1" + Thread.currentThread().getName() + "开始执行");
                //模拟线程耗时操作
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //可以通过一些开关。判断业务是否正确完毕。然后让计数器减一。放在这里可以防止业务代码报错导致主线程一直阻塞
                countDownLatch.countDown();
                System.out.println("子线程1" + Thread.currentThread().getName() + "执行完毕");
            }
        });
        executorService.shutdown();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.execute(() -> {
            //doWorking
            try {
                System.out.println("子线程2" + Thread.currentThread().getName() + "开始执行");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("子线程2" + Thread.currentThread().getName() + "执行完毕");
                countDownLatch.countDown();
            }
        });
        executorService1.shutdown();
        try {
            //让主线程等待其他线程完成
            System.out.println("主线程1--等待其他线程完毕，才能执行");
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            System.out.println(allStackTraces.size());
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程都执行完毕");



        // 测试 计数器是否可以复用.结果线程直接走到  "主线程2--等待其他线程完毕，才能执行"  这一步，说明再次条用CountDownLatch.await 方法没有阻塞主线程

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        executorService2.execute(() -> {
            //doWorking
            try {
                System.out.println("子线程3" + Thread.currentThread().getName() + "开始执行");
                //模拟线程耗时操作
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //可以通过一些开关。判断业务是否正确完毕。然后让计数器减一。放在这里可以防止业务代码报错导致主线程一直阻塞
                countDownLatch.countDown();
                System.out.println("子线程3" + Thread.currentThread().getName() + "执行完毕");
            }
        });
        executorService2.shutdown();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        executorService3.execute(() -> {
            //doWorking
            try {
                System.out.println("子线程4" + Thread.currentThread().getName() + "开始执行");
                //模拟线程耗时操作
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //可以通过一些开关。判断业务是否正确完毕。然后让计数器减一。放在这里可以防止业务代码报错导致主线程一直阻塞
                countDownLatch.countDown();
                System.out.println("子线程4" + Thread.currentThread().getName() + "执行完毕");
            }
        });
        executorService3.shutdown();
        try {
            //让主线程等待其他线程完成
            System.out.println("主线程2--等待其他线程完毕，才能执行");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程都执行完毕");
    }
}
