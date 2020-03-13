package com.example.demo.multithread.day4;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @description: 利用通道进行 线程间通讯
 * @author: chenxue
 * @create: 2019-12-20 09:59
 **/
public class PipeDemo {
    public static void main(String[] args) {
        try {
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream();
            //out.connect(inputStream); 和inputStream.connect(out); 只能使用一个，否则会报错
            // 底层调用 out.connect(in) 和 输入流建立连接。并且是一一对应的。即一个输入管道 对应一个输出管道.并且不可以在一个线程中同时调用者两个管道。会导致死锁
            inputStream.connect(out);
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    writeData.write(out);
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    readData.read(inputStream);
                }
            });
            t2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WriteData {
    public void write(PipedOutputStream out){
        System.out.println("Write:");
        try {
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i+1);
                // 调用这个方法时，底层调用的是 输入管道  PipedInputStream的 receive 同步方法
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            out.flush();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ReadData{
    public void read(PipedInputStream in){
        try {
            System.out.println("Read:");
            byte[] limit = new byte[20];
            int length = in.read(limit);
            while(length != -1){
                System.out.print(new String(limit,0,length));
                length = in.read(limit);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
