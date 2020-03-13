package com.example.demo.activity.utils;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * @description: activity 自带的生产id，在高并发环境下可能会重复。需要重写主键生成策略
 * @author: chenxue
 * @create: 2019-10-10 16:14
 **/
public class IdGen implements IdGenerator {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Override
    public String getNextId() {
        return IdGen.uuid();
    }
}
