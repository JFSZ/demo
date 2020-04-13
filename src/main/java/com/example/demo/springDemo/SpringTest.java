package com.example.demo.springDemo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-04-13 11:02
 **/
public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
    }
}
