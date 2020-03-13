package com.example.demo.multithread.day2;

/**
 * @description: volaitle 非原子性 即没有同步性
 * @author: chenxue
 * @create: 2019-12-17 14:50
 **/
public class VolatitleDemo1 {
    public static void main(String[] args) {
        VolatitleDemo1Test[] test = new VolatitleDemo1Test[100];
        for (int i = 0; i < 100; i++) {
            test[i] = new VolatitleDemo1Test();
        }
        for (int i = 0; i < 100; i++) {
            test[i].start();
        }
    }
}

class VolatitleDemo1Test extends Thread{
    volatile public static int count;
    public void add(){
        for (int i = 0; i <100 ; i++) {
            count ++;
        }
        System.out.println("count = " + count);
    }
    @Override
    public void run() {
        add();
    }
}
