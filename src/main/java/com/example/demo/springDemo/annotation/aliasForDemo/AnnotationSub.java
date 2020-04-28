package com.example.demo.springDemo.annotation.aliasForDemo;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @description: 测试@AliasFor注解的继承性
 * @author: chenxue
 * @create: 2020-04-28 13:57
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AnnotationBase
public @interface AnnotationSub {
    @AliasFor(annotation = AnnotationBase.class,attribute = "value")
    String subValue() default "location";

    @AliasFor(annotation = AnnotationBase.class)
    String location() default "location";
}
