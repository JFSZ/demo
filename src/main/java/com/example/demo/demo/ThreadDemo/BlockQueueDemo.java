package com.example.demo.demo.ThreadDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 使用阻塞队列 实现生产者、消费者模式
 * @author: cx
 * @create: 2020-03-30 23:04
 **/
public class BlockQueueDemo {
    public static void main(String[] args) {
        BlockData blockData = new BlockData();
        for (int i = 0; i < 4; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        blockData.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < 6; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        blockData.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
class BlockData{
    private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(MAX_NUM);
    private static final Integer MAX_NUM = 20;
    private boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //生产
    public void increment() throws InterruptedException {
        while (flag){
            boolean offer = blockingQueue.offer(atomicInteger.incrementAndGet());
            if(offer){
                System.out.println("生产数据成功:" + atomicInteger.get() + "队列长度：" + blockingQueue.size());
            }else {
                System.out.println("生产数据失败:" + atomicInteger.get() + "队列长度：" + blockingQueue.size());
            }
            Thread.sleep(1000);
        }
        System.out.println("停止生产!");
    }

    //消费
    public void decrement() throws InterruptedException {
        while (flag){
            Integer poll = blockingQueue.poll();
            System.out.println("消费数据:" + poll);
            Thread.sleep(1000);
        }
        System.out.println("停止消费!");
    }
    //停止
    public void stop(){
        this.flag = false;
    }
}
