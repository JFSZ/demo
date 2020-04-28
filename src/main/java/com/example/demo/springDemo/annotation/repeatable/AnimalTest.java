package com.example.demo.springDemo.annotation.repeatable;

public class AnimalTest {
    public static void main(String[] args) {
        if(Test.class.isAnnotationPresent(Animals.class)){
            Animals animals = Test.class.getAnnotation(Animals.class);
            for (Animal animal : animals.value()){
                System.out.println(animal.name());
            }
        }

    }

}

@Animal(name = "Tim",subject = "Dog")
@Animal(name = "Rock",subject = "Cat")
class Test{

}
