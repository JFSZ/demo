package com.example.demo.web;

import com.example.demo.annotation.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author chenxue
 * @Description 日志系统controller
 * @Date 2019/8/14 11:38
 */

@RestController
@RequestMapping("/log")
public class LogController {
    @Log(methodName = "测试",operateName = "Tom")
    @PostMapping("/doLog")
    public Object doLog(){
        System.out.println("LogController --启动doLog()方法，");
        return new HashMap<String,Object>().put("name","Tom");
    }
}
