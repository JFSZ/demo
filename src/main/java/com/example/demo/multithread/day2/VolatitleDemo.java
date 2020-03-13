package com.example.demo.multithread.day2;

/**
 * @description: volatitle 解决死循环以及多线程中的变量可见性
 *  解决同步死循环
 * @author: chenxue
 * @create: 2019-12-17 11:15
 **/
public class VolatitleDemo {
    public static void main(String[] args) {
        VolatitleDemoTest test = new VolatitleDemoTest();
        new Thread(test).start();
        try {
            Thread.sleep(3);
            System.out.println("停止线程");
            test.setFlag(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//优化 以多线程实现
class VolatitleDemoTest implements Runnable{
    volatile private boolean flag = true;
    public void run1(){
        int i = 0;
        while(flag){
            System.out.println("线程名称:" + Thread.currentThread().getName() + "第" + i++ + "次出现");
        }
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        run1();
    }
}
