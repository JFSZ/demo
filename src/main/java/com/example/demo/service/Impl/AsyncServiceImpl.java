package com.example.demo.service.Impl;

import com.example.demo.service.AsyncService;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author chenxue
 * @Description 异步service
 * @Date 2019/8/19 9:22
 */

@Service
@Transactional
public class AsyncServiceImpl implements AsyncService {

    /*@Override
    public void doTest(){
        AsyncServiceImpl service = (AsyncServiceImpl) AopContext.currentProxy();
        service.doAsync();
    }*/

    @Override
    @Async
    public void doAsync() {
        try {
            Thread.sleep(3000);
            System.out.println("方法执行结束：" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
