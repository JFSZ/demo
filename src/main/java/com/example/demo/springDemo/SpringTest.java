package com.example.demo.springDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-04-13 11:02
 **/
public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        context.getBean("");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.getBean("");
        ClassPathResource res = new ClassPathResource("");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyBeanConfig.class);
        applicationContext.register(MyBeanFactoryPostProcessor.class);
        applicationContext.refresh();
        BeanDemo beanDemo = (BeanDemo)applicationContext.getBean("beanDemo");
        System.out.println(beanDemo.toString());
    }
}

