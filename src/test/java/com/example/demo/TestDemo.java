package com.example.demo;

import com.example.demo.utils.MyArrayList;
import com.example.demo.utils.MyLinkList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chenxue
 * @Description
 * @Date 2019/8/15 14:55
 */

public class TestDemo {
    @Test
    public void demo1(){
        boolean flag = true;
        User user = new User();
        user.setUserName("Tom");
        user.setUserAge("22");
        User1 user1 = new User1();
        BeanUtils.copyProperties(user,user1);
        System.out.println(user1.getUserage() + " " + user1.getUsername());
    }

    @Test
    public void demo2(){
       /* Date date = Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        System.out.println(date);
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String st = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), ZoneId.of("Asia/Shanghai")));
        System.out.println(st);*/

        System.out.println(StringUtils.isBlank("  "));
        System.out.println(StringUtils.isEmpty("  "));
    }

    private static int count = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    @Test
    public void test() throws InterruptedException {

        for (int i = 0; i < 10000; i++) {
            new Thread(){
                @Override
                public void run() {
                    count ++;
                    atomicInteger.getAndIncrement();
                }
            }.start();
        }
        Thread.sleep(1000);
        System.out.println(count);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void demo3(){
        Thread t1 = new Thread(() -> {
            try {
                System.out.println( Thread.currentThread().getName()+ "执行");
                LockSupport.park();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("finally块 ，输出语句");
            }
        },"a");
        t1.start();
        Thread t2 = new Thread(() -> {
            System.out.println( Thread.currentThread().getName() + "执行");
        },"b");
        t2.start();
    }

    /**
     * @Description:  Collections 工具类使用
     * @Author: chenxue
     * @Date: 2020/4/21  16:48
     */
    @Test
    public void demo4(){
        //Collections.emptyList() 返回一个静态list，大小为0.这样会节省创建对象的开销。并且返回的list不可以add、remove方法
        List<String> list = Collections.emptyList();
        System.out.println(list.size());
        //报错  java.lang.UnsupportedOperationException
        //list.add("1");
    }



}
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class User{
    private String userName;
    private String userAge;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class User1{
    private String username;
    private String userage;
}

