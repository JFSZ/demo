package com.example.demo.multithread.day4;

/**
 * @description: 创建多个线程，实现有序的操作
 * @author: chenxue
 * @create: 2019-12-20 13:51
 **/
public class DBTools {
    public static void main(String[] args) {
        DBToolsTest test = new DBToolsTest();
        for (int i = 0; i < 10; i++) {
            ThreadA threadA = new ThreadA(test);
            ThreadB threadB = new ThreadB(test);
            threadA.start();
            threadB.start();
        }
    }
}
class ThreadA extends Thread{
    private DBToolsTest toolsTest;
    @Override
    public void run() {
        toolsTest.read();
    }
    ThreadA(DBToolsTest toolsTest){
        this.toolsTest = toolsTest;
    }

}

class ThreadB extends Thread{
    private DBToolsTest toolsTest;
    @Override
    public void run() {
        toolsTest.save();
    }
    ThreadB(DBToolsTest toolsTest){
        this.toolsTest = toolsTest;
    }

}

class DBToolsTest{
    volatile private boolean flag = false;
    synchronized public void save(){
            try {
                while(flag) {
                    wait();
                }
                System.out.println("write");
                flag = true;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    synchronized public void read(){
        try {
            while (!flag){
                wait();
            }
            System.out.println("read");
            flag = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
