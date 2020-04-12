package com.example.demo.demo.lock;


import java.util.concurrent.*;

/**
 * @description: 是一个用于线程间协作的工具类，用于两个线程间交换数据
 * 主要用于两个线程之间的数据交换。如果有多个线程的话。具有随机性。或者其他的线程会一直阻塞
 * 子线程如果出错，主线程是无法捕捉的。因为设计线程之初就是为了让各个线程互不干扰。
 * 其实原因很简单，run()方法是我们调用start()方法后，jvm再去调用run()方法，所以throws抛出异常是往上一级的调用者抛，调用者是jvm，抛给jvm java是不允许的，
 * 所以，run()方法只能try,catch处理异常；无法使用throws来处理异常。
 * @author: chenxue
 * @create: 2020-04-06 18:07
 **/
public class ExchangerDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            Exchanger<String> exchanger = new Exchanger<>();
            Product product = new Product(exchanger);
            Customer customer = new Customer(exchanger);
            Future<?> submit = executorService.submit(product);
            submit.get();
            Future<?> submit1 = executorService.submit(customer);
            submit1.get();
        } catch (Exception e) {
            System.out.println(1123);
            executorService.shutdownNow();
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
class Product implements Runnable{
    private Exchanger<String> exchanger;

    public Product(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run(){
        boolean flag = false;
        try {
            String str = "Product";
            System.out.println("Product交换前。。。" + str);
            str = exchanger.exchange(str,1,TimeUnit.SECONDS);
            flag = true;
            System.out.println("Product交换后。。。" + str);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }finally {
            throw new RuntimeException();
        }
    }
}

class Customer implements Runnable{
    private Exchanger<String> exchanger;

    public Customer(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            String str = "Customer";
            System.out.println("Customer交换前。。。" + str);
            TimeUnit.SECONDS.sleep(3);
            str = exchanger.exchange(str);
            System.out.println("Customer交换后。。。" + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
