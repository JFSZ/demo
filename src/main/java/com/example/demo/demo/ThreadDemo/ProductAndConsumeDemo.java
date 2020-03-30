package com.example.demo.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 生产、消费模式
 * 1、 使用 wait/notify
 * 存在的问题.1: java.lang.IllegalMonitorStateException 报错 原因：用了Object 的wait 和 notify 当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。
 * 2:  while(list.size() == 20) 这里必须使用while  不可以用if 会导致虚假唤醒线程
 * @author: chenxue
 * @create: 2020-03-30 10:12
 **/
public class ProductAndConsumeDemo {
    public static void main(String[] args) {
        List<ProductData> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new Product(list));
        }

        for (int i = 0; i < 6; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new Consume(list));
        }
        //生产者
       /* for (int i = 0; i < 4; i++) {
            Product product = new Product(list);
            Thread thread = new Thread(product);
            thread.start();
        }

        for (int i = 0; i < 6; i++) {
            Consume product = new Consume(list);
            Thread thread = new Thread(product);
            thread.start();
        }*/

    }

}

/**
 * @Description: 生产者
 * @Author: chenxue
 * @Date: 2020/3/30  10:38
 */
class Product implements Runnable{
    //容器 这里用 ArrayList只是练习，其实真实生产中不推荐用它.对ArrayList做增删操作耗时.而且线程不安全
    private List<ProductData> list;

    public Product(List<ProductData> list) {
        this.list = list;
    }


    @Override
    public void run() {
        create();
    }

    /**
     * @Description: 生产数据
     * @param
     * @Author: chenxue
     * @Date: 2020/3/30  11:09
     */
    public void create(){
        synchronized (list){
            while(true){
                while(list.size() == 20){
                    try {
                        list.wait();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ProductData productData = ProductData.randomData();
                list.add(productData);
                System.out.println("添加数据为:" + productData.getName() + ":" + productData.getPrice());
                list.notify();
            }
        }
    }
}


/**
 * @Description: 消费者
 * @Author: chenxue
 * @Date: 2020/3/30  10:38
 */
class Consume implements Runnable{
    //容器
    private List<ProductData> list;

    public Consume(List<ProductData> list) {
        this.list = list;
    }

    @Override
    public void run() {
        consumer();
    }

    //消费数据
    public void consumer(){
        synchronized (list){
            while (true){
                while (list.size() == 0){
                    try {
                        list.wait();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //随机移除一个数据
                ProductData remove = list.remove(new Random().nextInt(list.size()));
                System.out.println("移除的数据为:" +remove.getName() + ":" + remove.getPrice());
                list.notify();
            }
        }
    }
}

/**
 * @Description: 数据
 * @Author: chenxue
 * @Date: 2020/3/30  10:56
 */
class ProductData{
    public ProductData(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // 产品名称
    private String name;
    //价格
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static ProductData randomData(){
        return new ProductData("" + new Random().nextInt(10),new Random().nextDouble()*1000);
    }
}