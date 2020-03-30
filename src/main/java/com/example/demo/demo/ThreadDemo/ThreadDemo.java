package com.example.demo.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: 线程demo 测试
 * 并发三要素: 原子性 可见性 有序性
 * 出现线程安全的原因:
 * 线程切换带来原子性问题  缓存导致可见性 编译优化带来有序性
 *
 * 当下 更推荐使用 Executors 创建线程，并且可以使用线程池技术
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
                System.out.println("Thread 创建多线程");
            }
        });

        //线程 Thread 方式
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 创建多线程");
            }
        });
        thread.start();
        // Runnable 接口
        RunDemo runDemo = new RunDemo();
        Thread t1 = new Thread(runDemo);
        t1.start();



        //Callable 接口FutureTask 实现多线程
        //使用线程池 submit
        // ExcutorService submit() 与execute()区别
        // submit 有返回值并且可以捕捉到线程的异常。参数也不一致。  execute() 没有返回值，也不可以捕捉异常
        MyCallAble myCallAble = new MyCallAble();
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            /*FutureTask<String> futureTask = new FutureTask<String>(myCallAble);
            executorService.execute(futureTask);*/
            Future<String> submit = executorService.submit(myCallAble);
            list.add(submit);
        }
        executorService.shutdown();
        for (Future<String> future : list){
            try {
                String s = future.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
                //有异常关闭
                executorService.shutdownNow();
            }
        }


       /*
       利用 Thread 执行 FutureTask
       MyCallAble myCallAble = new MyCallAble();
        FutureTask<String> futureTask = new FutureTask<String>(myCallAble);
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        try {
            String s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

    }

    static class RunDemo implements Runnable{

        @Override
        public void run() {
            //do something
        }
    }

}

class MyCallAble implements Callable<String>{
    public volatile int count;
    @Override
    public String call() throws Exception {
        do{
            if(count == 3)
                throw new RuntimeException("有内鬼，终止交易!");
            System.out.println(++ count);
        }while (count < 10);
        return Thread.currentThread().getName() + count + " callAble 返回的值";
    }
}
