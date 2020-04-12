package com.example.demo.demo.myenum;

public enum MyEnum {
    A{
        public long doWork(){return 1L;}
    },
    B{

    };
    public long doWork(){
        throw new AbstractMethodError();
    }
}
