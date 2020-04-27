package com.example.demo.springDemo;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-04-14 13:41
 **/
@Configuration
public class MyBeanConfig {
    @Autowired
    private UserTest user;
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

@Component
class UserTest{
    private String name = "Tom";
}
