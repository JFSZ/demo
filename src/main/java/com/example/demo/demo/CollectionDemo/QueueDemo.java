package com.example.demo.demo.CollectionDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description: 队列demo BlockingDeque 阻塞队列
 * 1、在队列为空时，获取元素的线程会等待队列变为非空。
 * 2、当队列满时，存储元素的线程会等待队列可用。
 * 阻塞队列是线程安全的。
 * @author: chenxue
 * @create: 2020-03-26 16:48
 **/
public class QueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
    }
}
