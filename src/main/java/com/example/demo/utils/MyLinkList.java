package com.example.demo.utils;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * @description: 手写实现linklist
 * @author: chenxue
 * @create: 2020-03-19 15:20
 **/
public class MyLinkList<E> {
    //链表长度
    transient int size;

    //双向链表头部节点
    transient Node<E> first;

    //双向链表尾部节点
    transient Node<E> last;

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
        //检查index合法性
        checkIndex(index);
        Object[] object = c.toArray();
        int addSize = object.length;
        // 首先需要找到他所在index 位置的原有 节点 以及它的前一个节点 所以这里需要定义两个节点变量
        Node<E> prev,curr;
        //这又分两种情况 1、尾部添加 2、头部添加
        if (index == size){
            // 在尾部 新增节点
            prev = last;
            curr = null;
        }else {
            // 需要先找到下标为index 的节点
            curr = node(index);
            prev = curr.prev;
        }
        // 把要添加的集合 转换为节点对象
        for (Object o : object){
            Node<E> node = new Node(prev,o,null);
            //原有集合 为null 情况
            if (prev == null)
                first = node;
            else
                prev.next = node;
            prev = node;
        }
        //把 index所在的节点 和新增 节点信息关联上
        if(curr == null){
            last = prev;
        }
        else{
            prev.next = curr;
            curr.prev = prev;
        }
        modCount ++;
        size += addSize;
        return true;
    }

    //添加元素
    public boolean add(E e){
        lastAdd(e);
        return true;
    }

    //头部新增
    private void firstAdd(E e){
        Node<E> addNode = new Node(null,e,first);
        Node<E> prev = first;
        first = addNode;
        if(prev == null){
            last = addNode;
        }else {
            prev.prev = addNode;
        }
        size++;
        modCount++;
    }

    private void lastAdd(E e){
        Node<E> lastNode = last;
        Node<E> newNode = new Node(lastNode,e,null);
        last = newNode;
        // 新增节点 已经指向前个节点，但是前个节点的next并美哦与指向新增节点
        if(lastNode == null)
            first = newNode;
        else
            lastNode.next = newNode;
        modCount++;
        size++;
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

    //删除指定位置元素
    public E remove(int index){
        checkIndex(index);
        return doRemove(node(index));
    }

    //删除指定元素
    public boolean remove(E e){
        if( e == null){
            for (Node<E> i = first; i != null;  i = i.next ) {
                if( i.item == null){
                    doRemove(i);
                    return true;
                }
            }
        }else {
            for (Node<E> i = first; i != null ; i = i.next) {
                if(e.equals(i.item)){
                    doRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    //执行删除操作
    private E doRemove(Node<E> element){
        E e = element.item;
        Node<E> prev = element.prev;
        Node<E> next = element.next;
        if(prev == null){
            first = next;
        }else {
            prev.next = next;
            element.prev = null;
        }
        if(next == null){
            last = prev;
        }else {
            next.prev = prev;
            element.next = null;
        }
        modCount ++;
        size --;
        return e;
    }

    //根据下标获取元素
    public E get(int index){
        checkIndex(index);
        return node(index).item;
    }

    //设置元素
    public E set(int index,E e){
        checkIndex(index);
        Node<E> node = node(index);
        E oldElement = node.item;
        node.item = e;
        return oldElement;
    }

    //获取h头部节点
    public E getFirst(){
        final Node<E> f = first;
        if(f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    //获取尾部 节点
    public E getLast(){
        final Node<E> l = last;
        if(l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    //获取集合 size
    public int size(){
        return size;
    }

    //入栈 默认在头部新增
    public void push(E e){
        firstAdd(e);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        for (Node i = first; i != null ; i = i.next) {
            sb.append(i.item);
        }
        return sb.toString();
    }
}
