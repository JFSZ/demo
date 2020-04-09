package com.example.demo.demo.lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行； 多个线程互等，等大家都完成，再携手共进。
 * 模拟 赛马跑道问题。
 * @author: chenxue
 * @create: 2020-04-06 18:06
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7);
        Test test = new Test(cyclicBarrier);
        for (int i = 0; i < 5; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(test);
            executorService.shutdown();
        }

    }
    static class Test implements Runnable{
        private static CyclicBarrier cyclicBarrier;
        private static int count = 0;
        private int index = count ++;
        private static Random random = new Random();
        public Test(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }
        public void doWork(){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < count; i++) {
                sb.append("*");
            }
            System.out.println("*" + index );
        }
        @Override
        public void run() {
            try {
                while (Thread.interrupted()){
                    synchronized (this){
                        count += random.nextInt(3);
                    }
                    cyclicBarrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        public synchronized int getCount(){
            return count;
        }
    }
}
