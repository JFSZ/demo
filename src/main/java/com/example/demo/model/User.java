package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * @author chenxue
 * @Description User实体类
 * @Date 2019/8/15 15:10
 */
@TableName("user")
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private static User user = new User();

    public User() {
        System.out.println("User 构造方法被调用");
    }

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public static User getInstance(){
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
