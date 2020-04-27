package com.example.demo.springDemo.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Animals.class)
public @interface Animal {
     String name() default "Tom";
     String subject() default "Cat";

}
