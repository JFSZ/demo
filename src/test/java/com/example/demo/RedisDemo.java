package com.example.demo;

import com.example.demo.utils.RedisUtil;
import lombok.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @description:
 * @author: chenxue
 * @create: 2019-12-04 11:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemo {

    @Autowired
    private RedisUtil util;
    @Test
    public void test(){
        Person person = new Person();
        person.setAge(11);
        person.setName("小明");
        person.setBirdaty(new Date());
        util.lSet("user",person);
        Object o = util.lGet("user",0,1);
        System.out.println(o);
        if(o instanceof Person){
            Person person1 = (Person)o;
            System.out.println(person1.getName());
        }
    }
}
@Data
class Person{
    private String name;
    private Integer age;
    private Date birdaty;

    public Person() {
    }

    public Person(String name, Integer age, Date birdaty) {
        this.name = name;
        this.age = age;
        this.birdaty = birdaty;
    }
}
