package com.example.demo.springDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-04-13 11:02
 **/
public class SpringTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyBeanConfig.class);
        applicationContext.register(MyBeanFactoryPostProcessor.class);
        applicationContext.refresh();
        BeanDemo beanDemo = (BeanDemo)applicationContext.getBean("beanDemo");
        System.out.println(beanDemo.toString());
    }
}

