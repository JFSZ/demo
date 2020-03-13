package com.example.demo.datasources.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.demo.datasources.dsenum.DataSourceEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxue
 * @Description 动态数据源配置类
 * @Date 2019/9/20 11:32
 */

@Configuration
public class MyDataSourcesConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.ds1")
    public DataSource firstDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.druid.ds2")
    public DataSource secondDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @DependsOn({"firstDataSource","secondDataSource"})
    public DynamicDataSource dataSource(DataSource firstDataSource,DataSource secondDataSource){
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceEnum.DS1.getValue(),firstDataSource);
        targetDataSource.put(DataSourceEnum.DS2.getValue(),secondDataSource);
        return new DynamicDataSource(firstDataSource,targetDataSource);
    }
}
