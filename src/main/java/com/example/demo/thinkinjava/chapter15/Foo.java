package com.example.demo.thinkinjava.chapter15;

public class Foo<T> {
    T var;
    public T getT(){
        return var;
    }
    public void setT(T t){
        var = t;
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setName("Tom");
        Foo<Cat> foo = new Foo<>();
        foo.setT(cat);
        Cat t = foo.getT();
        System.out.println(t.getName());
    }
}
class Cat{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}