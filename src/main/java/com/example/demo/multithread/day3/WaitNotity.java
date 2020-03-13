package com.example.demo.multithread.day3;



/**
 * @description: 多线程之间消息传递 wait(Long) 线程在等待固定时间后，是否有线程对锁进行唤醒。如果超时自动唤醒
 * wait  会暂时让出锁，以便其他线程可以得到锁。但是notify并不会释放锁。
 * sleep 会阻塞当前线程，并不会释放锁
 * @author: chenxue
 * @create: 2019-12-19 15:15
 **/
public class WaitNotity {
    public static void main(String[] args) {
        String lock = "A";
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        System.out.println("线程进入等待中。。。");
                        //对象上必须有锁，才可以调用wait方法
                        lock.wait(10000);
                        System.out.println("线程被唤醒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    lock.notify();
                    System.out.println("开始随机唤醒线程");
                    for (int i = 0; i < 10 ; i++) {
                        System.out.println("notify 是否是立即释放锁" + i);
                    }
                }
            }
        });
        t2.start();


    }
}
