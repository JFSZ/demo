package com.example.demo.utils;

import java.util.Collection;

/**
 * @description: 手写实现linklist
 * @author: chenxue
 * @create: 2020-03-19 15:20
 **/
public class MyLinkList<E> {
    //链表长度
    private int size;

    //双向链表头部节点
    private Node<E> first;

    //双向链表尾部节点
    private Node<E> last;

    //标识链表操作次数
    private int modCount;

    //无参构造
    public MyLinkList(){

    }

    //有参构造.因为是链表形式，所以没有知道容器大小的构造方法
    public MyLinkList(Collection<? extends E> c){
        this();
        addAll(c);
    }

    // 新增操作
    public boolean addAll(Collection<? extends E> c){
        return addAll(size,c);
    }

    //指定位置新增操作
    public boolean addAll(int index,Collection<? extends E> c){
        //检测 index 是否合法
        checkIndex(index);
        //执行新增操作
        Object[] a = c.toArray();
        //新增长度
        int temSize = a.length;
        // prev 前一个节点 succ 下个节点
        Node<E> prev,succ;
        //新增节点位置，是在尾部添加还是在链表中间添加
        if(index == size){
            succ = null;
            prev = last;
        }else {
            succ = node(index);
            prev = succ.prev;
        }
        // 把 a添加到节点中
        for (Object o : a){
            Node<E> node = new Node(prev,o,null);
            if(prev == null)
                first = node;
            else
                prev.next = node;
            prev = node;
        }
        // 如果是在 尾部新增
        if(succ == null){
            last = prev;
        }else {
            prev.next = succ;
            succ.prev = prev;
        }
        size +=temSize;
        modCount++;
        return true;
    }

    //查找 下标 index 所在的节点信息 利用二分法快速查找
    private Node<E> node(int index){
        if(index < (size << 1)){
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }

    }
    //检测链表下标
    private void checkIndex(int index){
        if(index < 0 || index >size)
            throw new IndexOutOfBoundsException("下标越界!");
    }
    //内部类 维护节点信息
    private static class Node<E>{
        //指向前一个节点，保存前一个节点信息
        private Node<E> prev;
        // 指向后一个节点信息,保存后一个节点信息
        private Node<E> next;
        private E item;
        Node(Node<E> prev,E element,Node<E> next){
            this.prev = prev;
            this.next = next;
            this.item = element;
        }
    }

}
