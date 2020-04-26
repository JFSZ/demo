package com.example.demo.springDemo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class MyBeanPostProcessorTest implements InitializingBean {
    private String name;
    private String age;

    public MyBeanPostProcessorTest() {
        System.out.println("MyBeanPostProcessorTest 无参构造器被调用!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 被调用");
        this.age = "22";
        this.name = "Tom";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setname 被调用");
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        System.out.println("setAge 被调用");
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyBeanPostProcessorTest{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @PostConstruct
    public void init(){
        System.out.println("init-method 方法执行");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy-method 方法执行");
    }
}
