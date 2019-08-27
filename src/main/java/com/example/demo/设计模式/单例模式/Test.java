package com.example.demo.设计模式.单例模式;

import com.example.demo.设计模式.单例模式.demo1.DCLSingle;
import com.example.demo.设计模式.单例模式.demo1.SingleDemo;
import com.example.demo.设计模式.单例模式.demo1.SingleDemo1;

/**
 * @author chenxue
 * @Description 测试
 * @Date 2019/8/21 11:42
 */

public class Test {
    public static void main(String[] args) {
        //懒汉模式 校验
        /*Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SingleDemo singleDemo = SingleDemo.getInstance();
                System.out.println(singleDemo+ "==1");
            }
        },"Test123");
        t.start();
        new Runnable() {
            @Override
            public void run() {
               SingleDemo singleDemo = SingleDemo.getInstance();
                System.out.println(singleDemo + "==2");
            }
        }.run();*/

        //饿汉模式校验
        /*SingleDemo1 singleDemo1 = SingleDemo1.getInstance();
        System.out.println(singleDemo1);
        SingleDemo1 singleDemo2 = SingleDemo1.getInstance();
        System.out.println(singleDemo2);*/

        //双重校验锁 测试
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                DCLSingle dclSingle = DCLSingle.getInstance();
                System.out.println(dclSingle + "====1");
            }
        },"DCL");
        try {
            synchronized (t){
                t.start();
                t.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Runnable(){
            @Override
            public void run() {
                DCLSingle dclSingle = DCLSingle.getInstance();
                System.out.println(dclSingle + "====2");
            }
        }.run();
        synchronized (t){
            t.notify();
        }

    }
}
