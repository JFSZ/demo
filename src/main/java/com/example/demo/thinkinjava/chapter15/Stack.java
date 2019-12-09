package com.example.demo.thinkinjava.chapter15;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @description: 泛型化 堆栈. 使用泛型参数
 * @author: chenxue
 * @create: 2019-10-15 15:25
 **/
public class Stack<E> {
    private E[] item;
    private static final int DEFAULT_INITTAL_CAPACITY = 16;
    private int size = 0;
    //初始化
    Stack(){
        item = (E[]) new Object[DEFAULT_INITTAL_CAPACITY];
    }
    //入栈
    public void push(E o){
        if(size == item.length)
        item = Arrays.copyOf(item, 2 * size + 1);
        item[size++] = o;
    }
    //出栈
    public E pop(){
        if(size == 0){
            throw new RuntimeException("我是有底线的...");
        }
        E object = (E) item[--size];
        item[size] = null;
        return object;
    }

    public static void main(String[] args) {
       /* Stack stack = new Stack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/
        Stack<String> stack = new Stack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        while (stack.size > 0){
            System.out.println(stack.pop());
        }
    }
}
