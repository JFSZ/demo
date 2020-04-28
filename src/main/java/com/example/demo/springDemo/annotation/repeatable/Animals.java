package com.example.demo.springDemo.annotation.repeatable;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Animals {
    Animal[] value();
}
