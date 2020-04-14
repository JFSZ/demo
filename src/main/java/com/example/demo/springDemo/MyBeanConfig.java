package com.example.demo.springDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-04-14 13:41
 **/
@Configuration
public class MyBeanConfig {
    @Bean("beanDemo")
    public BeanDemo a1(){
        BeanDemo beanDemo = new BeanDemo();
        beanDemo.setAge(1);
        beanDemo.setName("TOm");
        return beanDemo;
    }

    @Bean("beanDemo2")
    public BeanDemo a2(){
        BeanDemo beanDemo = new BeanDemo();
        beanDemo.setAge(21);
        beanDemo.setName("Marry");
        return beanDemo;
    }

}
