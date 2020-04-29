package com.example.demo.springDemo.annotation.typeFilter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(value = "com.example.demo",
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type= FilterType.CUSTOM,classes = MyFilter.class)})
public @interface MyTypeFilter {
    String value() default "";
}
