package com.example.demo.设计模式.策略者模式;

/**
 * @author chenxue
 * @Description
 * @Date 2019/8/21 9:31
 */

public class Context {
    private Animal animal;
    public Context(){}

    public void eat(){
        animal.eat();
    }
    public void sleep(){
        animal.sleep();
    }
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
