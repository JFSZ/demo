package com.example.demo.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: 就是一个信号量，它的作用是限制某段代码块的并发数
 *
 * @author: chenxue
 * @create: 2020-04-06 18:06
 **/
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(6);
    static ExecutorService service = Executors.newCachedThreadPool();
    public static void main(String[] args) {
        for (int i = 0; i < 18; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "执行");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
