package com.example.demo;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: chenxue
 * @create: 2020-03-09 14:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties(prefix = "email")
@Configuration
@PropertySource("classpath:application-dev.yml")// 指明配置文件
@Data
public class ConfigurationPropertiesDemo {
    private FoxMail foxmail = new FoxMail();
    private YaHoo yaHoo = new YaHoo();
    @Data
    class FoxMail{
        private String username;
        private String pwd;
    }
    @Data
    class YaHoo{
        private String username;
        private String pwd;
    }

    @Test
    public void demo1(){
        System.out.println(foxmail.username + "--" + foxmail.pwd);
        System.out.println(yaHoo.username + "--" + yaHoo.pwd);
    }
}
