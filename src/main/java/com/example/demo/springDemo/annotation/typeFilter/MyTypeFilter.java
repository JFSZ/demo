package com.example.demo.springDemo.annotation.typeFilter;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTypeFilter {
    String value() default "";
}
