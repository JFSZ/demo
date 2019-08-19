package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author chenxue
 * @Description 用户UserService
 * @Date 2019/8/19 11:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
