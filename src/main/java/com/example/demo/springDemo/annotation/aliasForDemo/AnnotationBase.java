package com.example.demo.springDemo.annotation.aliasForDemo;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @description: @Alis 继承性测试 以及 别名的显式/隐式声明别名。以及传递性测试
 * 比如: location 和 code 就是互为别名
 * 主要用途可以组合注解。形成新的注解。
 * @author: chenxue
 * @create: 2020-04-28 13:55
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface AnnotationBase {
    @AliasFor(value = "location")
    String value() default "value";

    @AliasFor(value = "value")
    String location() default "value";

    @AliasFor(value="value")
    String code() default "";
}
