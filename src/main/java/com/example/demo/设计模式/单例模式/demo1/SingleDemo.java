package com.example.demo.设计模式.单例模式.demo1;

/**
 * @author chenxue
 * @Description 单例模式 懒汉方式创建
 * @Date 2019/8/21 11:40
 */

public class SingleDemo {
    private static SingleDemo singleDemo;
    private SingleDemo(){

    }
    public static synchronized SingleDemo getInstance(){
        if(singleDemo == null){
            if(Thread.currentThread().getName().equals("Test123")){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            singleDemo =new SingleDemo();
        }
        return singleDemo;
    }

}
