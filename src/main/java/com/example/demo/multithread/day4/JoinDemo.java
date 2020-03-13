package com.example.demo.multithread.day4;


/**
 * @description: join sleep 区别 join 内部使用wait 所以会释放锁。而sleep 不会释放锁
 * @author: chenxue
 * @create: 2019-12-20 15:35
 **/
public class JoinDemo {
    public static void main(String[] args) {
        JoinDemoTest test = new JoinDemoTest();
        test.setName("A");
        test.start();
        try {
            test.join();
            System.out.println("等待线程A执行完之后，执行这句话");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class JoinDemoTest extends Thread{
    @Override
    public void run() {
        int random = (int)(Math.random() * 10000);
        try {
            System.out.println("休眠:" + random + "秒");
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
