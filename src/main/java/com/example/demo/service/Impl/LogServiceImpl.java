package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.LogDao;
import com.example.demo.model.LogBean;
import com.example.demo.service.LogService;
import org.springframework.stereotype.Service;

/**
 * @author chenxue
 * @Description service
 * @Date 2019/8/14 10:55
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, LogBean> implements LogService {
}
