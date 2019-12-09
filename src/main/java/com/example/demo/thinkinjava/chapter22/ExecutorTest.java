package com.example.demo.thinkinjava.chapter22;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 使用Executor 调度线程
 * @author: chenxue
 * @create: 2019-11-29 10:52
 **/
public class ExecutorTest {
    public static void main(String[] args) {
        executor();
    }
    private static boolean flag = true;

    public static void executor(){

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AtomicInteger atomicInteger = new AtomicInteger();
        while(flag){
            if(atomicInteger.get()<= 100){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("123");
                        atomicInteger.getAndIncrement();
                        System.out.println("执行" + atomicInteger.get() + "任务");
                    }
                });
            }else {
                if(((ThreadPoolExecutor)executorService).getActiveCount() == 0){
                    executorService.shutdown();
                    flag = false;
                    System.out.println("任务结束");
                }
            }

        }
    }


    public void test(){

    }
}
