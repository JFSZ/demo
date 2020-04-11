package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.User;

/**
 * @author chenxue
 * @Description 用户service
 * @Date 2019/8/19 11:53
 */

public interface UserService extends IService<User> {
    void doWork(User user);

    void doWork1(User user);
}
