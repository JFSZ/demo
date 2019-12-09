package com.example.demo.thinkinjava.chapter22;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.*;

/**
 * @description: 利用Future实现网购厨具，超市买菜。做饭 流程 达到多线程效果
 * @author: chenxue
 * @create: 2019-12-02 09:43
 **/
public class FutureCook {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        long startTime = System.currentTimeMillis();
        Callable<Chuju> onlineShopping = new Callable<Chuju>() {
            @Override
            public Chuju call() throws Exception {
                System.out.println("第一步：下单");
                System.out.println("第一步：等待送货");
                Thread.sleep(5000);  // 模拟送货时间
                System.out.println("第一步：快递送到");
                return new Chuju();
            }
        };
        FutureTask<Chuju> task = new FutureTask<Chuju>(onlineShopping);
        new Thread(task).start();
        Thread.sleep(2000);//模拟购买食材
        Shicai shicai = new Shicai();
        System.out.println("第二步:超市购买食材!");
        if(!task.isDone()){// 联系快递,查询是否到货
            System.out.println("第三步:厨具还没到!心情好就等,心情不好就退货!");
        }
        Chuju chuju = task.get();
        if(chuju == null){
            System.out.println("不等快递，太慢了。退货");
            return;
        }
        System.out.println("厨具到，开始展现厨艺吧!");
        cook(chuju,shicai);
        System.out.println("总共耗时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    static void cook(Chuju chuju,Shicai shicai){};
    static class Chuju{}
    static class Shicai{}
}
