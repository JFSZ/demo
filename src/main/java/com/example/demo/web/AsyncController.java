package com.example.demo.web;

import com.example.demo.annotation.Log;
import com.example.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxue
 * @Description 异步方法 测试
 * @Date 2019/8/19 9:18
 */

@RestController
@RequestMapping("/sync")
public class AsyncController {
    @Autowired
    private AsyncService service;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @Log(methodName = "AsyncTest",operateName = "Dom")
    public String AsyncTest(){
        Long start = System.currentTimeMillis();
        service.doAsync();
        System.out.println("任务成功执行，耗时:====" + (System.currentTimeMillis() - start));
        return "任务成功执行，耗时:====" + (System.currentTimeMillis() - start);
    }
}
