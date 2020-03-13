package com.example.demo.thinkinjava.chapter22;

/**
 * @description: 守护线程 练习
 * @author: chenxue
 * @create: 2019-12-12 10:24
 **/
public class DaemonTest extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 6; i++) {
            System.out.println("当前i = " + i);
        }
    }
}
class DaemonTest2{
    public static void main(String[] args) {
        DaemonTest test = new DaemonTest();
        test.setDaemon(true);
        test.start();
        try {
            Thread.sleep(5000);
            System.out.println("守护线程退出");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
