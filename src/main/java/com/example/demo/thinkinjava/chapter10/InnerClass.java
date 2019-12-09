package com.example.demo.thinkinjava.chapter10;

/**
 * @description: 内部类 广义上分四种 成员内部类 局部内部类 匿名内部类 静态内部类
 *              .this .new 用法
 * @author: chenxue
 * @create: 2019-09-30 12:01
 **/
public class InnerClass {
    private String name ;
    public class Dog{
        private String name;
        public void eat(){
            InnerClass.this.name = "Tom";
        }
    }
    public Dog brking(){
        return new Dog();
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        Dog dog = innerClass.brking();
        Dog dog1 = innerClass.new Dog();
        dog.eat();
        System.out.println(dog.name);
        System.out.println(innerClass.name);
    }
}
