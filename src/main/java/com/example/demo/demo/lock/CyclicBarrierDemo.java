package com.example.demo.demo.lock;


import java.util.ArrayList;
import java.util.List;
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
    private static int count = 7;
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        HorseTest horseTest = new HorseTest(service);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, horseTest);
        for (int i = 0; i < count; i++) {
            HorseDemo horseDemo = new HorseDemo(cyclicBarrier);
            horseTest.add(horseDemo);
            service.execute(horseDemo);
        }
    }

    static class HorseTest implements Runnable{
        private static List<HorseDemo> horseDemos = new ArrayList<>();
        private static final int MAX_NUM = 70;
        private ExecutorService service;
        public HorseTest(ExecutorService service) {
            this.service = service;
        }

        @Override
        public void run() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < MAX_NUM; i++) {
                sb.append("=");
            }
            System.out.println(sb);

            StringBuffer stringBuffer = new StringBuffer();
            for (HorseDemo horseDemo : horseDemos){
                System.out.println(horseDemo.doWork());
            }
            for (HorseDemo horseDemo : horseDemos){
                if(horseDemo.getCount() >= MAX_NUM){
                    System.out.println("winner is " + horseDemo);
                    service.shutdownNow();
                    return;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void add(HorseDemo horseDemo){
            horseDemos.add(horseDemo);
        }
    }
}

class HorseDemo implements Runnable{
    private int count = 0;
    private CyclicBarrier cyclicBarrier;
    private static int order = 0;
    public int index = order++;
    private Random random = new Random();

    public HorseDemo(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
                try {
                    synchronized (this) {
                        count += random.nextInt(3);
                    }
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
    }

    public String doWork(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getCount(); i++) {
            sb.append("*");
        }
        sb.append(index);
        return sb.toString();
    }

    public synchronized int getCount(){
        return count;
    }

    @Override
    public String toString() {
        return "Horse " + index;
    }
}

