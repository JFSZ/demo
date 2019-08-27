package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author chenxue
 * @Description
 * @Date 2019/8/15 14:55
 */

public class TestDemo {
    @Test
    public void demo1(){
        boolean flag = true;
        User user = new User();
        user.setUserName("Tom");
        user.setUserAge("22");
        User1 user1 = new User1();
        BeanUtils.copyProperties(user,user1);
        System.out.println(user);
    }

    @Test
    public void demo2(){
       /* Date date = Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        System.out.println(date);
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String st = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), ZoneId.of("Asia/Shanghai")));
        System.out.println(st);*/

        System.out.println(StringUtils.isBlank("  "));
        System.out.println(StringUtils.isEmpty("  "));
    }

}
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class User{
    private String userName;
    private String userAge;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class User1{
    private String username;
    private String userage;
}

