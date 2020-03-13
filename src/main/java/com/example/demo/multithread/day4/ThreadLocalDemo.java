package com.example.demo.multithread.day4;

/**
 * @description: ThreadLocal 为每个线程保存共享变量
 * InheritableThreadLocal  子线程继承父线程，可以用它取得父线程的线程内变量 子类中可以对继承的父类变量做出修改
 * @author: chenxue
 * @create: 2019-12-20 16:44
 **/
public class ThreadLocalDemo {
    public static void main(String[] args) {
        InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
        inheritableThreadLocal.set("父线程");
        InheritableThreadLocalTool tool = new InheritableThreadLocalTool();
        tool.set("父线程内添加");
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("ABC");
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(threadLocal.get());
                    System.out.println(inheritableThreadLocal.get());
                    System.out.println(tool.get());
                }
            });
            t.start();

        }
    }
}
class InheritableThreadLocalTool extends InheritableThreadLocal{
    @Override
    protected Object childValue(Object parentValue) {
        return parentValue + "子类中添加";
    }
}
