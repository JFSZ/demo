package com.example.demo.thinkinjava.chapter22;

/**
 * @description: Future 类测试
 * @author: chenxue
 * @create: 2019-12-02 09:25
 **/
public class CommonCook {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        OnlineShopping onlineShopping = new OnlineShopping();
        onlineShopping.start();
        onlineShopping.join();
        Thread.sleep(3000);//模拟买菜
        Shicai shicai = new Shicai();
        System.out.println("第二步:超市买菜");
        cook(onlineShopping.chuju,shicai);
        System.out.println("第三步:开始做饭");
        System.out.println("总共耗时" + (System.currentTimeMillis() - startTime) + "ms");

    }
    static class OnlineShopping extends Thread{
        private Chuju chuju;

        @Override
        public void run() {
            // 1、购买厨具 2、等待发货 3、收到厨具
            System.out.println("第一步:网购厨具，下单。");
            try {
                //模拟等待
                Thread.sleep(2000);
                System.out.println("等待收货");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //收到
            chuju = new Chuju();
            System.out.println("收到厨具");
        }
    }
    static void cook(Chuju chuju,Shicai shicai){};
    static class Chuju{}
    static class Shicai{}
}
