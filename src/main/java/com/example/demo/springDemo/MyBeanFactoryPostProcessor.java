package com.example.demo.springDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自定义后置bean处理器
 * @author: chenxue
 * @create: 2020-04-14 10:07
 **/
@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用后置处理bean开始。。。");
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            String name = beanDefinitionNames[i];
            if("beanDemo".equalsIgnoreCase(name)){
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
                System.out.println("修改beanDemo对象的age属性...");
                beanDefinition.getPropertyValues().add("age","50");
                System.out.println(name + "bean properties " + beanDefinition.getPropertyValues().toString());
            }
        }
        System.out.println("调用后置处理bean结束。。。");
    }
}
