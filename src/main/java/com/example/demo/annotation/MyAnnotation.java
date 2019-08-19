package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @author chenxue
 * @Description 自定义注解
 * @Date 2019/8/13 10:44
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    public String name();
    int age() default 18;
    int[] score();
}
