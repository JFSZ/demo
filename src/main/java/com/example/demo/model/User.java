package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenxue
 * @Description User实体类
 * @Date 2019/8/15 15:10
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    public User( String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
