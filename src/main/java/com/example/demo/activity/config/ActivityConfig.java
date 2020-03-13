package com.example.demo.activity.config;

import com.example.demo.activity.utils.IdGen;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-10-10 16:12
 **/
public class ActivityConfig implements ProcessEngineConfigurationConfigurer {
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setIdGenerator(new IdGen());
    }
}
