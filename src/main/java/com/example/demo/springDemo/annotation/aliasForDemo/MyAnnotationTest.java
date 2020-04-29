package com.example.demo.springDemo.annotation.aliasForDemo;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.util.function.Consumer;

/**
 * @description:测试@AliasFor注解 1.别名，注解到两个实现上标识互为别名。这里互为别名的属性必须相等。否则会报错。
 * @author: chenxue
 * @create: 2020-04-28 11:06
 **/
public class MyAnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Consumer<MyAnnotation> consumer = myAnnotation -> {
            Assert.isTrue("Tom".equalsIgnoreCase(myAnnotation.value()),"");
            Assert.isTrue(myAnnotation.location().equalsIgnoreCase(myAnnotation.value()),"");
        };

        MyAnnotation myAnnotation = AnnotationUtils.findAnnotation(MyAnnotationDemo.class.getMethod("doWork"), MyAnnotation.class);
        MyAnnotation myAnnotation1 = AnnotationUtils.findAnnotation(MyAnnotationDemo.class.getMethod("doWorking"), MyAnnotation.class);
        consumer.accept(myAnnotation);
        consumer.accept(myAnnotation1);


        Consumer<AnnotationSub> consumer1 = a -> {
            Assert.isTrue(a.location().equalsIgnoreCase(a.subValue()),"相等");
            System.out.println(a.location() + " --- " + a.subValue());
        };
        AnnotationSub sub = AnnotatedElementUtils.findMergedAnnotation(AnnotationDemo.class.getMethod("test"),AnnotationSub.class);
        AnnotationSub sub1 = AnnotatedElementUtils.findMergedAnnotation(AnnotationDemo.class.getMethod("test1"),AnnotationSub.class);
        consumer1.accept(sub);
        consumer1.accept(sub1);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfigTest.class);
        MyConfigTest myConfig = (MyConfigTest)applicationContext.getBean("myConfigTest");
        System.out.println(myConfig.toString());


    }

    public static void doWork(Object o) {
    }
}

class MyAnnotationDemo{
    @MyAnnotation(location = "Tom",value = "Tom")
    public void doWork(){}
    @MyAnnotation(value = "Tom")
    public void doWorking(){}
}


class AnnotationDemo{
    @AnnotationSub(subValue = "Tom")
    public void test(){}

    @AnnotationSub(location = "Rock")
    public void test1(){}
}

@MyConfig(name = "myConfigTest")
class MyConfigTest{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
