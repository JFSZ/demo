package com.example.demo.web;

import com.example.demo.annotation.Log;
import com.example.demo.annotation.UserBody;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenxue
 * @Description 用户Controller 测试缓存cache基本用法
 * @Date 2019/8/19 11:50
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void save(@UserBody User user){
        //redisUtil.set("user",user,30000);
        service.save(user);
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    @Cacheable(cacheNames = "user")
    @Log(methodName = "getUserInfo",operateName = "Tom")
    public User getUserInfo(@RequestParam Integer id){
        System.out.println("查询" + id + "号员工");
        return service.getById(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @CachePut(cacheNames = "user",key = "#user.id")
    public void update(@RequestBody User user){
        service.saveOrUpdate(user);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @CacheEvict(value = "user",key = "#id")
    public void delete(@RequestParam Integer id){
        service.removeById(id);
    }

}
