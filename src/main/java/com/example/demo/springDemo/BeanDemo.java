package com.example.demo.springDemo;

import lombok.Data;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-04-14 13:44
 **/
@Data
public class BeanDemo {
    private String name;
    private int age;

    public static void main(String[] args) {

    }
}

@Data
class Person{
    private Fruit fruit;
    private String name;

    public void eat(Fruit fruit){
        System.out.println(this.name + "在吃：" + fruit.getName() + "水果，形状为:" + fruit.getShape());
    }
}

@Data
class Fruit{
    private String name;
    private String shape;
}
