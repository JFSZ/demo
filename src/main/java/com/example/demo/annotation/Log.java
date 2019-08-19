package com.example.demo.annotation;

import java.lang.annotation.*;

@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    public String methodName();
    public String operateName();

}
