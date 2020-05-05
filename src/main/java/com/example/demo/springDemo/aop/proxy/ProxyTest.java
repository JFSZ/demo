package com.example.demo.springDemo.aop.proxy;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * @description: 动态代理
 * @author: chenxue
 * @create: 2020-05-05 10:17
 **/
public class ProxyTest {
    public static void main(String[] args) {
        //
        PersonProxy personProxy = new StudentProxy();
        //动态代理
        PersonProxy proxy = (PersonProxy) Proxy.newProxyInstance(PersonProxy.class.getClassLoader(),
                new Class[]{PersonProxy.class},
                new ProxyHandlerImpl<PersonProxy>(personProxy));
        //
        proxy.doWork();

        //生成指定的代理类。
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", StudentProxy.class.getInterfaces());
        String path = "E:\\idea-workspace\\demo\\src\\main\\java\\com\\example\\demo\\springDemo\\aop\\proxy\\stuproxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }

}
