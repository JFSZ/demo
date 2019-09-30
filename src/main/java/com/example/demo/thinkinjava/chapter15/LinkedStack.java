package com.example.demo.thinkinjava.chapter15;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 自定义 泛型堆栈 实现内部链式存储机制
 * @author: chenxue
 * @create: 2019-09-29 17:04
 **/
public class LinkedStack<T> {
    //内部维护的节点对象
    private  class Node{
        T item;//当前节点对象存储的的 值
        Node next; // 前一个节点对象
        Node(){
            item = null;
            next = null;
        }
        Node(T item,Node next){
            this.item = item;
            this.next = next;
        }
        boolean end(){
            return item == null && next == null;
        }
    }

    private Node top = new Node();
    public void push(T item){
        top = new Node(item,top);
    }
    public T pop(){
        T result = top.item;
        if(!top.end()){
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack <String> linked = new LinkedStack<>();
        for (String item: "hello nice world".split(" ")){
            linked.push(item);
        }
        String result = null;
        while((result = linked.pop()) != null){
            System.out.println(result);
        }
    }
}

