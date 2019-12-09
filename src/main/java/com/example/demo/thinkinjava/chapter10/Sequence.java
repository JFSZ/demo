package com.example.demo.thinkinjava.chapter10;


/**
 * @description: 模拟迭代器 iteator
 * @author: chenxue
 * @create: 2019-10-11 17:41
 **/
public class Sequence {
    private Object[] item;
    private int next = 0;
    public Sequence(int size){
        item = new Object[size];
    }
    /**
     * @Description:  新增
     * @param x
     * @Author: chenxue
     * @Date: 2019/10/11  17:59
     */
    public void add(Object x){
        if(next < item.length){
            item[next ++] = x;
        }
    }
    private class SequenceSelector implements Selcector{
        private int i = 0;
        @Override
        public boolean end() {
            return i == item.length;
        }

        @Override
        public Object current() {
            return item[i];
        }

        @Override
        public void next() {
            if(i < item.length){
                i ++;
            }
        }
    }
    public Selcector selector(){
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10;i ++){
            sequence.add(i);
        }
        Selcector selcector = sequence.selector();
        while (!selcector.end()){
            System.out.println(selcector.current());
            selcector.next();
        }
    }

}
