package com.example.demo.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author chenxue
 * @Description 测试自定义注解
 * @Date 2019/8/13 10:49
 */

public class StudentDemo {
    @MyAnnotation(name = "Tom",age = 22,score = 123)
    public void study(int times){
        for (int i = 0; i < times; i ++){
            System.out.println("Good Good Study, Day Day Up");
        }
    }

    public static void main(String[] args) {
        try {
            Class cls = Class.forName("com.example.demo.annotation.StudentDemo");
            Method method = cls.getMethod("study",int.class);
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            if(myAnnotation != null){
                System.out.println("study 方法上有 MyAnnotation 注解,注解为: name:" + myAnnotation.name() + "age:" + myAnnotation.age() + "scoe:" + Arrays.toString(myAnnotation.score()));
            }
            myAnnotation.age();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
