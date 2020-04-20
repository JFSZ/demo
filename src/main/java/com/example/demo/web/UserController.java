package com.example.demo.web;

import com.example.demo.annotation.Log;
import com.example.demo.datasources.annotation.DataSource;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    public void save(@RequestBody User user){
        redisUtil.set("user",user,30000);
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

    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    @Cacheable(cacheNames = "userList")
    @Log(methodName = "getUserList",operateName = "Tom")
    @DataSource(name = "ds1")
    public List<User> getUserList(){
        System.out.println("查询所有员工");
        return service.list();
    }


    /**
     * @Description: 测试 Transactional 如果没有设置 rollbackFor 属性  则默认只会对未检查异常（继承自 RuntimeException 的异常）或者 Error 异常进行回滚
     * 1、主方法中添加 事务,那么调用的方法如果发生异常(前提是子方法未捕捉)。都会回滚事务。
     * 2、主方法中没有事务，子方法中有事务。异常发送在主方法中，无法回滚。
     *3、主方法中有事务，异常发生在子方法中，但是子方法对异常进行捕捉。则不会回滚
     * @param
     * @Author: chenxue
     * @Date: 2020/4/11  8:55
     */
    @PostMapping("/doWork")
    public void doWork(){
        service.doWork(createUser());
        service.doWork1(createUser());
        int a = 1/0;
    }

    public User createUser(){
        Random random = new Random();
        User user = new User("Tom",random.nextInt(100),"360.com");
        return user;
    }


    private static Lock lock = new ReentrantLock();
    /**
     * @Description: 测试接口多线程
     * @param
     * @Author: chenxue
     * @Date: 2020/4/11  11:38
     */
    @PostMapping("/doSync")
    public String doSync(){
        ExecutorService executorService = Executors.newCachedThreadPool();
      /*  try {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //doWorking 模拟业务
                        Thread.sleep(10000);
                        System.out.println("业务完成");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }*/
        lock.lock();
        try {
            //模拟业务处理时间
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "业务完成");
        return "SUCCESS";
    }

    public static void main(String[] args) {
        UserController controller = new UserController();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                controller.doSync();
            },"Thread-" + i);
            thread.start();
        }
    }

}
