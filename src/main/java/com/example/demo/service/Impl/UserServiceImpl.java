package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author chenxue
 * @Description 用户UserService
 * @Date 2019/8/19 11:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public void doWork(User user) {
        int insert = userDao.insert(user);
        System.out.println("dowork ==" +  (insert == 1) );
       /* try {
            int a = 1/0;
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    @Override
    @Transactional
    public void doWork1(User user) {
        int insert = userDao.insert(user);
        System.out.println("dowork1 ==" +  (insert == 1) );
    }
}
