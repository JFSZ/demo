package com.example.demo.activity.controller;

import com.example.demo.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxue
 * @Description
 * @Date 2019/9/7 16:40
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @RequestMapping("/start")
    public boolean startActivity(){
        return activityService.startActivity();
    }
}
