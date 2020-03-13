package com.example.demo.thinkinjava.chapter22;

/**
 * @description: 线程练习
 * @author: chenxue
 * @create: 2019-12-09 13:52
 **/
public class TaskTest1 extends Thread{
    private int count = 5;
    TaskTest1(){
        super();
    }
    TaskTest1(String name){
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        count --;
        System.out.println("线程" + this.currentThread().getName() + "-计算为:" + count);
    }
}

class Run {
    public static void main(String[] args) {
        /*TaskTest1 t1 = new TaskTest1("A");
        TaskTest1 t2 = new TaskTest1("B");
        TaskTest1 t3 = new TaskTest1("C");
        TaskTest1 t4 = new TaskTest1("D");
        TaskTest1 t5 = new TaskTest1("E");
        TaskTest1 t6 = new TaskTest1("F");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();*/
        TaskTest1 t = new TaskTest1();
        Thread t1 = new Thread(t,"A");
        Thread t2 = new Thread(t,"B");
        Thread t3 = new Thread(t,"C");
        Thread t4 = new Thread(t,"D");
        Thread t5 = new Thread(t,"E");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}