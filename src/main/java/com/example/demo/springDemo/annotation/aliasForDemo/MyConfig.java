package com.example.demo.springDemo.annotation.aliasForDemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@Configuration
public @interface MyConfig {
    @AliasFor(annotation = Component.class,attribute = "value")
    String name() default "";
}
