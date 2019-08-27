package com.example.demo.设计模式.代理模式.静态代理;

/**
 * @author chenxue
 * @Description 普通职员类
 * @Date 2019/8/21 17:27
 */

public class NomalEmployee implements Employee{
    private BossEmployee bossEmployee;
    public NomalEmployee(){}
    public NomalEmployee(BossEmployee bossEmployee){
        this.bossEmployee = bossEmployee;
    }

    @Override
    public void goWork(String userName) {
        System.out.println("员工：" + userName + "开始上班");
    }

    @Override
    public void doWork() {
        bossEmployee.doWork();
    }

    public BossEmployee getBossEmployee() {
        return bossEmployee;
    }

    public void setBossEmployee(BossEmployee bossEmployee) {
        this.bossEmployee = bossEmployee;
    }
}
