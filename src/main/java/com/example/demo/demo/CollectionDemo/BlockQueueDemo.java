package com.example.demo.demo.CollectionDemo;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 队列练习 队列分类。实现原理、常用方法。
 * @author: chenxue
 * @create: 2020-04-06 17:58
 **/
public class BlockQueueDemo {
    public static void main(String[] args) {
        // 指定容器大小，并且默认是非公平锁 底层使用Lock锁，实现同步
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(20);
        for (int i = 1; i <= 20; i++) {
            arrayBlockingQueue.offer("" + i);
        }
        System.out.println(arrayBlockingQueue.size());
        //offer() 此方法不允许添加null。 方法会在队列尾部添加元素，如果队列已满，则返回false,添加成功则返回true 。和add() 区别：add方法如果添加失败会抛出错误 IllegalStateException
        // offer(E e,Long timeout,TimeUnit timeUnit) 在队列尾部插入元素e ,如果队列已满则阻塞线程 timeout 时间。底层调用lock.lockInterruptibly()可以响应中断.
        //add方法，底层调用的是offer()
        //boolean offer = arrayBlockingQueue.offer("21");
        try {
            boolean offer = arrayBlockingQueue.offer("22", 1L, TimeUnit.SECONDS);
            System.out.println(offer);
            // 将指定的元素插入此队列的尾部，如果该队列已满，则等待可用的空间。会让线程进入等待
            //arrayBlockingQueue.put("23");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //boolean add = arrayBlockingQueue.add("21");
        //System.out.println(add);
        //System.out.println(offer);
        //poll 获取并移除队列头部元素，如果队列为空则返回null
        String poll = arrayBlockingQueue.poll();
        System.out.println(poll + " == " + arrayBlockingQueue.size());
        // peek 获取但不移除队列头部元素，如果队列为空则返回null
        String peek = arrayBlockingQueue.peek();
        System.out.println(peek);
        ArrayList<String> list = new ArrayList<>();
        // drainTo 把队列中所有元素移除，并放置在给定的集合Collection中
        arrayBlockingQueue.drainTo(list);
        System.out.println(list.toString());
        System.out.println(arrayBlockingQueue.size());
        //remainingCapacity 返回队列剩余可用容量
        System.out.println(arrayBlockingQueue.remainingCapacity());

        // 从此队列中移除指定元素的单个实例（如果存在）。 成功返回true 失败返回false
        boolean remove = arrayBlockingQueue.remove("2");
        System.out.println(remove);


    }
}
