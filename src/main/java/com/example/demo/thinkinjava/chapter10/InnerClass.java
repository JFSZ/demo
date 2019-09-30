package com.example.demo.thinkinjava.chapter10;

/**
 * @description: 内部类
 * @author: chenxue
 * @create: 2019-09-30 12:01
 **/
public class InnerClass {
    private String name;
    class Dog{
        public void eat(){
            name = "Tom";
        }
    }
    public Dog brking(){
        return new Dog();
    }

    public static void main(String[] args) {
        Dog dog = new InnerClass().brking();
    }
}
