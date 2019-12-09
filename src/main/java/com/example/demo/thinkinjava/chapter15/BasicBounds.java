package com.example.demo.thinkinjava.chapter15;

import java.awt.*;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-10-15 10:51
 **/
public class BasicBounds {
}
interface HasColor{
    java.awt.Color getColor();
}
class Colored<T extends HasColor>{
    T item;
    Colored(T item){
        this.item = item;
    }
    T getItem(){
        return item;
    }
    Color color(){
        return item.getColor();
    }
}