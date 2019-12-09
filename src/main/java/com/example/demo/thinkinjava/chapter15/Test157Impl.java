package com.example.demo.thinkinjava.chapter15;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-10-14 09:32
 **/
public class Test157Impl implements Test157 {

    public String draw(){
        return "draw";
    }

    public <T extends Test157> void getInfo(T t){
        t.add();
        t.eat();
    }

    public static <T> T multh(T t){
        return t;
    }

    public static void main(String[] args) {

    }

    @Override
    public void add() {

    }

    @Override
    public void eat() {

    }
}
