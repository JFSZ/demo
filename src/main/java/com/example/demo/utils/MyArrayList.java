package com.example.demo.utils;


import java.util.Arrays;
import java.util.Collection;

/**
 * @description: 自定义ArrayList 核心代码:数组扩容机制 每次扩容1.5倍
 * 底层:System.arraycopy(src,srcIndex,desc,descIndex,index)
 * @author: chenxue
 * @create: 2020-03-18 14:49
 **/
public class MyArrayList<E> {
    //默认数组容量
    private static final int DEFAULT_CAPACITY = 10;
    //空数组
    private static final Object[] EMPTY_ELEMENTDATA = {};
    //默认空数组
    private static final Object[] DEFAULT_EMPTY_ELEMENTDATA = {};
    //集合内元素的个数
    private int size;
    //内置数组 transient表示序列化时忽略，避免流操作是为null增加负担。这样就需要重写 writeObject 和readObject
    transient Object[] elementData;
    //最大容量 java 元数据默认占8位,所以 - 8
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    //无参构造
    public MyArrayList(){
        this.elementData = DEFAULT_EMPTY_ELEMENTDATA;
    }

    //有参构造 指定容器大小
    public MyArrayList(int initialCapacity){
        if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else if(initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else {
            throw new IllegalArgumentException("非法参数 initialCapacity " + initialCapacity);
        }
    }
    //指定数据构造器
    public MyArrayList(Collection<? extends E> c){
        elementData = c.toArray();
        // 给size 赋值，并判断如果指定集合 长度不为0时，并且如果elementData 数组不为Object 时转换为Object
        if((size = elementData.length) != 0){
            if(elementData.getClass() != Object[].class){
                elementData = Arrays.copyOf(elementData,size,Object[].class);
            }
        }else{
            elementData = EMPTY_ELEMENTDATA;
        }
    }
    //add 方法
    public boolean add(E e){
        isExtends(size + 1);
        elementData[size ++] = e;
        return true;
    }
    // 指定位置，添加元素
    public void add(int index,E e){
        rangeCheckForAdd(index);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = e;
        size ++;
    }
    public int size(){
        return size;
    }
    //获取数组元素个数
    public int getSize(){
        return size;
    }
    //获取指定位置的数组元素
    public E get(int index){
        rangeCheck(index);
        return (E)elementData[index];
    }

    //移除指定位置的元素
    public E remove(int index){
        rangeCheck(index);
        E e = (E)elementData[index];
        // 判断 e 是否是最后一个元素
        int removeSize = size - index - 1;
        if(removeSize > 0){
            //不是最后一个元素
            System.arraycopy(elementData,index+1,elementData,index,removeSize);
        }
        elementData[--size] = null;
        return e;
    }

    //移除数组内某元素 移除找到的第一个元素
    public boolean remove(Object e){
        if(e == null){
            for (int i = 0; i < size; i++) {
                if(elementData[i] == null){
                    fastRemove(i);
                    return true;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if(e.equals(elementData[i])){
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index){
        E e = (E)elementData[index];
        // 判断 e 是否是最后一个元素
        int removeSize = size - index - 1;
        if(removeSize > 0){
            //不是最后一个元素
            System.arraycopy(elementData,index+1,elementData,index,removeSize);
        }
        elementData[--size] = null;
    }

    //新增时检测
    private void rangeCheckForAdd(int index){
        //index > size 必须在数组内部添加，否则数组元素不连续
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("index:" + index + ",szie" + size);
    }

    public void rangeCheck(int index){
        if(index >= size)
            throw new IndexOutOfBoundsException("index:" + index + ",szie" + size);
    }

    //是否需要扩容
    public void isExtends(int minCapacity){
        if(elementData == DEFAULT_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY,minCapacity);
        }
        //检查是否需要扩容
        if(minCapacity - elementData.length > 0){
            //需要扩容
            grow(minCapacity);
        }
    }
    //扩容
    private void grow(int minCapacity){
        int oldSize = elementData.length;
        int newSize = oldSize + oldSize >> 1;
        if(newSize - minCapacity < 0){
            newSize = minCapacity;
        }
        if(newSize - MAX_CAPACITY > 0){
            newSize = hugeCapacity(newSize);
        }
        elementData = Arrays.copyOf(elementData,newSize);
    }
    // 判断最大容量
    private int hugeCapacity(int minCapacity){
        if(minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_CAPACITY) ? Integer.MAX_VALUE : MAX_CAPACITY;
    }

}
