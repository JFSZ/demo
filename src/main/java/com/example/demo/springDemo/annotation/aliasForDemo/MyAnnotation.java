package com.example.demo.springDemo.annotation.aliasForDemo;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * @Description: 测试自定义注解，主要测试@AliasFor 别名 .互为别名的属性必须定义默认值,并且默认值必须相等。
 * 别名可以显式/隐式 声明别名，并且别名具有传递性。底层是利用AOP实现
 * @Author: chenxue
 * @Date: 2020/4/28  10:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    @AliasFor(value = "location")
    String value() default "";

    @AliasFor(value = "value")
    String location() default "";
}
