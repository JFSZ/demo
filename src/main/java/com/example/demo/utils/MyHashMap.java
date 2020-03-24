package com.example.demo.utils;

import java.util.Map;
import java.util.Objects;

/**
 * @description: 手写HashMap 主要功能 put putAll 扩容原理以及扩容机制 如何解决hashcode碰撞问题 何时变为红黑树
 * @author: chenxue
 * @create: 2020-03-23 08:08
 **/
public class MyHashMap<K,V> {
    //初始容量 默认16
    private final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    //最大容量
    static final int MAX_CAPACITY = 1 << 30;
    //默认加载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //默认 由链表变味树形结构 阈值 超过这个阈值就有链表变为 红黑树
    private final int TREEIFY_THRESHOLD = 8;
    //红黑树变为链表时的阈值
    private final int UNTREEIFY_THRESHOLD = 6;
    //
    private final int MIN_TREEIFY_CAPACITY = 64;

    static class Node<K,V> implements Map.Entry<K,V>{
        final int hash;
        private K key;
        private V value;
        private Node<K,V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final int hashCode(){
            //利用Objects.hashCode()方法 更加安全。对null做了判断
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = value;
            this.value = value;
            return oldValue;
        }
    }

    //节点数组
    private Node<K,V>[] table;
    //数组内元素个数
    private int size;
    //加载因子
    float loadFactor;
    //数组扩容阈值
    int threshold;
    //无参构造
    public MyHashMap(){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    //有参构造 指定容器大小
    public MyHashMap(int capacity){
        this(capacity,DEFAULT_LOAD_FACTOR);
    }
    //指定容器大小和加载因子
    public MyHashMap(int initCapacity,float loadFactor){
        if(initCapacity < 0){
            throw new IllegalArgumentException("非法参数:" + initCapacity);
        }
        if(initCapacity > MAX_CAPACITY)
            initCapacity = MAX_CAPACITY;
        if(loadFactor < 0 || Float.isNaN(loadFactor)){// 小于0或者 加载因子不是数字
            throw new IllegalArgumentException("非法参数:" + loadFactor);
        }
        this.loadFactor = loadFactor;
        //计算扩容阈值
        threshold = tableSizeFor(initCapacity);
    }

    //指定 集合构造
    public MyHashMap(Map<? extends K,? extends V> map){

    }

    //目的 ：找到接近并且大于 cap 的2^n 整数.原理:通过移位以及与自己异或。
    // 把高位为1的后面全部变为1。然后再加1.就变为接近cap 的2^n整数
    static final int tableSizeFor(int cap){
        int n = cap -1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAX_CAPACITY) ? MAX_CAPACITY : (n+1);
    }
    //新增 元素
    public V put(K key,V value){
        //根据 hash 找到存储位置，如果该位置已有元素。再判断该节点是链表还是红黑树.如果是链表需要判断是否需要转换为红黑树
        // 没有元素直接插入
        return putValue(hash(key),key,value,false,true);
    }

    // 把元素放在容器内
    V putValue(int hash, K key, V value, boolean onlyIfAbsent,boolean evict){
        return value;
    }

    //计算hash 值
    int hash(Object key){
        return 0;
    }
}
