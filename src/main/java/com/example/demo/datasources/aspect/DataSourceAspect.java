package com.example.demo.datasources.aspect;

import com.example.demo.datasources.annotation.DataSource;
import com.example.demo.datasources.config.DynamicDataSource;
import com.example.demo.datasources.dsenum.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author chenxue
 * @Description
 * @Date 2019/9/20 15:36
 */

@Component
@Aspect
@Order(-1)
@Slf4j
public class DataSourceAspect {
    @Pointcut("@annotation(com.example.demo.datasources.annotation.DataSource)")
    public void dataSourcePointCut(){}

    @Around("dataSourcePointCut()")
    public Object beforePoint(ProceedingJoinPoint point) throws Throwable {
        log.info("【动态数据源切面】 -- 开始");
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);
        if(dataSource == null){
            log.info("【动态数据源切面】 -- 使用默认数据源,数据源名称为: " + DataSourceEnum.DS1.getValue());
            DynamicDataSource.setDataSource(DataSourceEnum.DS1.getValue());
        }else {
            log.info("【动态数据源切面】 -- 使用配置数据源,数据源名称为: " + dataSource.name());
            DynamicDataSource.setDataSource(dataSource.name());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.cleanDataSource();
            log.info("【动态数据源切面】 -- 关闭数据源 ");
        }
    }
}
