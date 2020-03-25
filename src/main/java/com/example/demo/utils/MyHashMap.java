package com.example.demo.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    //指定 集合构造 map为空 会导致空指针异常
    public MyHashMap(Map<? extends K,? extends V> map){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(map,false);
    }

    //把map放入数组中
    final void putMapEntries(Map<? extends K,? extends V> m,boolean evict){
        //这里会报空指针
        int s = m.size();
        if(s > 0){
            if(table == null){
                //原先数组为空
                float f = (s / loadFactor) + 1.0f;
                int t = ((f > (float) MAX_CAPACITY) ? MAX_CAPACITY : (int)f);
                if(t > threshold)
                    threshold = tableSizeFor(t);
            }else if(s > threshold){
                //扩容
                resize();
            }
            for(Map.Entry<? extends K,? extends V> entry : m.entrySet()){
                K key = entry.getKey();
                V value = entry.getValue();
                putValue(hash(key),key,value,false,evict);
            }
        }
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
        return putValue(hash(key),key,value,false,true);
    }

    // 把元素放在容器内
    // onlyIfAbsent:如果为true，则不更改现有值
    // evict 如果为false，则表处于创建模式
    V putValue(int hash, K key, V value, boolean onlyIfAbsent,boolean evict){
        //t : 数组
        // p
        // n 数组长度
        // i index 数组下标
        Node<K,V>[] t;Node<K,V> p;int n,i;
        //1、如果table 为空，则初始化数组
        if((t = table) == null || (n = t.length) == 0)
            n = (table = resize()).length;
        //(n-1) & hash  对hash 取数组长度的模，用来定位在数组中的下标
        if(((p = t[i = (n-1) & hash]) == null)){//如果在 i 位置没有元素，直接新建一个元素加入
            t[i] = newNode(hash,key,value,null);
        }else{//则表示发生hash碰撞。  下标 i 已经有元素，分两种情况:key 相等则直接替换原来元素。
            // key不相等，需要用链表或者红黑树追加元素
            Node<K,V> e;K k;
            if((p.hash == hash) &&
                    ((k = p.key) == key  || (key != null && key.equals(k)))){// 新增元素的hash 和key 与原i下标元素相同
                e = p;
            }else{// 不相等。追加
                if(p instanceof TreeNode){
                    //则添加进 红黑树
                }else {
                    //则用开放寻址法 或者 拉链法
                    for(int j = 0; ;++j){
                        //循环终止条件 最后一盒元素添加上去就跳出循环. jdk1.8 链表插入方式为 尾部插入
                        if((e = p.next) == null){
                            p.next = new Node<>(hash,key,value,null);
                            if(j > TREEIFY_THRESHOLD - 1){//需要由链表转换为红黑树
                                treeifyBin(table,hash);
                            }
                            break;
                        }
                        //另外的循环终止条件 最后一盒元素添加上去就跳出循环. jdk1.8 链表插入方式为 尾部插入
                        if(e.hash == hash && ( (k =e.key) == key || (key != null && key.equals(k)))){
                            break;
                        }
                        p = e;
                    }
                }

            }

        }
        return value;
    }

    //链表转换为红黑树
    final void treeifyBin(Node<K,V>[] table,int hash){

    }

    //创建Node方法
    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next){
        return new Node<>(hash,key,value,next);
    }

    //计算hash 值 扰动函数。目的:1、一定要尽可能降低hash碰撞，越分散越好；
    // 2、算法一定要尽可能高效，因为这是高频操作, 因此采用位运算；
    //这样 高半位 和 低半位 做异或，达到混合原始哈希吗的高位和低位，加大低位的随机性
    //而且混合后的低位掺杂了高位的部分特征，这样高位的信息也被变相保留下来
    int hash(Object key){
        int h;
        return (key == null) ? 0:(h = key.hashCode()) ^ (h >>> 16);
    }

    //扩容
    private Node<K,V>[] resize(){
        return null;
    }

    //TreeNode
    static final class TreeNode<K,V>  extends MyHashMap.Node<K,V> {
        TreeNode<K,V> parent;
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;
        boolean red;
        public TreeNode(int hash,K key,V value,Node<K,V> next){
            super(hash,key,value,next);
        }
    }
}
