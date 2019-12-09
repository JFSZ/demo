package com.example.demo.effectiveJava.one;

/**
 * @description: 用静态工厂代替构造器 可以返回当前类型的子类对象
 * @author: chenxue
 * @create: 2019-10-16 11:16
 **/
public class StaticFactory {
    public static StaticFactory getInstance(){
        return SubStatic.getInstance();
    }
}
class SubStatic extends StaticFactory{
    public static SubStatic getInstance(){
        return new SubStatic();
    }
}
