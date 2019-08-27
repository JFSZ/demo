package com.example.demo.设计模式.代理模式.静态代理;

/**
 * @author chenxue
 * @Description Boss职员类
 * @Date 2019/8/21 17:27
 */

public class BossEmployee implements Employee {
    private String userName;
    public BossEmployee(){}
    public BossEmployee(String userName){
        this.userName = userName;
    }
    @Override
    public void goWork(String userName) {
        System.out.println("员工：" + userName + "开始上班");
    }

    @Override
    public void doWork() {
        System.out.println("员工:"+ userName + "开始工作");
    }
}
