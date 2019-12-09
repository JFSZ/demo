package com.example.demo.thinkinjava.chapter15;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-10-14 13:57
 **/
public class ArrayMark<T> {
    private Class<T> kind;
    ArrayMark(Class<T> kind){
        this.kind = kind;
    }
    T[] create(int size){
        return (T[]) Array.newInstance(kind,size);
    }

    public static void main(String[] args) {
        ArrayMark<String> arrayMark = new ArrayMark<>(String.class);
        String[] strings = arrayMark.create(8);
        System.out.println(Arrays.toString(strings));


    }
}
