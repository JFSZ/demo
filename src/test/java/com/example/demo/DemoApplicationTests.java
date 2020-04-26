package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.datasources.annotation.DataSource;
import com.example.demo.model.User;
import com.example.demo.springDemo.BeanDemo;
import com.example.demo.springDemo.MyBeanPostProcessorTest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties(prefix = "myconfig")
@Data
public class DemoApplicationTests {

    @Resource
    private CacheManager cacheManager;
    @Resource
    private UserDao userDao;
    @Test
    public void contextLoads() {
        List<User> userList = userDao.selectList(null);
        Assert.assertEquals(5,userList.size());
        userList.forEach(System.out::println);

    }


    @Test
    public void test1(){
        System.out.println(StringUtils.join(cacheManager.getCacheNames(),","));
        Cache cache = cacheManager.getCache("user");
        cache.put("key","123");
        System.out.println("缓存成功");
        String res = cache.get("key",String.class);
        System.out.println(res);
    }

    @Test
    public void test2(){
        User user = userDao.selectById(1);
        user.setName("测试1");
        userDao.updateById(user);
    }
    @Test
    @DataSource(name = "ds2")
    public void test3(){
        User user = userDao.selectById(1);
        user.setName("测试2");
        userDao.updateById(user);
    }

    private String name;
    private Integer age;
    private Boolean enable;

    @Test
    public void test4(){
        System.out.println(name);
        System.out.println(age);
        System.out.println(enable);
    }


    @Autowired
    private BeanDemo beanDemo;

    @Autowired
    private BeanDemo beanDemo2;
    @Test
    public void test5(){
        System.out.println(beanDemo.toString());
        System.out.println(beanDemo2.toString());
    }

    @Autowired
    private MyBeanPostProcessorTest test;
    @Test
    public void test6(){
        System.out.println(test.getName());
        System.out.println(test.getAge());
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j <=  i; j++) {
                System.out.print(j  + "* "+ i + " = " + i*j + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test7(){
        MyLookUpDemo myLookUpDemo = new MyLookUpDemo() {
            @Override
            MyLookUpParent getBean() {
                return new MyLookUpChild();
            }
        };
        myLookUpDemo.doSomething();
    }


}
@ComponentScan
class MyLookUpParent{
    public void showMe(){
        System.out.println("i am parent");
    }
}

class MyLookUpChild extends  MyLookUpParent{
    @Override
    public void showMe() {
        super.showMe();
        System.out.println("i am child");
    }
}
abstract class MyLookUpDemo{

    public void doSomething(){
        this.getBean().showMe();
    }

    @Lookup
    abstract MyLookUpParent getBean();
}
