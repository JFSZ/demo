package com.example.demo.springDemo.annotation.typeFilter;

import com.example.demo.model.User;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;


public class MyFilterTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestDemo.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str : beanDefinitionNames){
            System.out.println(str);
        }
    }
}

@Configuration
@MyTypeFilter
class TestDemo{
    private String name = "Tom";

    @Bean
    public User getUser(){
        return new User();
    }
    @Override
    public String toString() {
        return this.name + "sss";
    }
}
