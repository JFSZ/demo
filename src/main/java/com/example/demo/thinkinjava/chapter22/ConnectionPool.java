package com.example.demo.thinkinjava.chapter22;

import java.util.concurrent.*;

/**
 * @description: 模拟连接池 Future方式创建连接,避免重复创建
 * @author: chenxue
 * @create: 2019-12-02 15:20
 **/
public class ConnectionPool {
    //连接池
    private ConcurrentHashMap<String, FutureTask<Connection>> pool = new ConcurrentHashMap<>();
    public Connection getConnection(String key) throws ExecutionException, InterruptedException {
        FutureTask<Connection> task = null;
        if(pool.contains(key)){
            task = pool.get(key);
        }else {
            // 在多线程 情况下 可能会有多个连接。导致资源浪费。不论有多少线程，只执行一次 createConnection()
            Callable<Connection> callable = new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    return createConnection();
                }
            };
            FutureTask<Connection> newTask = new FutureTask<Connection>(callable);
            task = pool.putIfAbsent(key,newTask);
            if(task == null){
                task = newTask;
                task.run();
            }
            pool.put(key,task);
        }
        return task.get();
    }

    public Connection  createConnection(){
        return new Connection();
    }
    class Connection{};
}
