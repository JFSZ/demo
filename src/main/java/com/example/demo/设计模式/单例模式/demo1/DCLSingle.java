package com.example.demo.设计模式.单例模式.demo1;

/**
 * @author chenxue
 * @Description 双重校验锁
 * @Date 2019/8/21 14:36
 */

public class DCLSingle {
    private static DCLSingle dclSingle;
    private DCLSingle(){}
    public static DCLSingle getInstance(){
        if(dclSingle == null){
            synchronized (DCLSingle.class){
                if(Thread.currentThread().getName().equals("DCL")){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dclSingle = new DCLSingle();
            }
        }
        return dclSingle;
    }

}
