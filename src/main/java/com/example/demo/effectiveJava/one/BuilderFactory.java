package com.example.demo.effectiveJava.one;

/**
 * @description: 遇到多个构造器参数时要考虑使用构建器。使用建造者模式
 * @author: chenxue
 * @create: 2019-10-17 11:44
 **/
public class BuilderFactory {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder{
        private final int servingSize;
        private final int servings;

        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;
        public Builder(int servingSize,int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val){
            calories = val;
            return this;
        }
        public Builder fat(int val){
            fat = val;
            return this;
        }
        public Builder sodium(int val){
            sodium = val;
            return this;
        }
        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }
        public BuilderFactory build(){
            return new BuilderFactory(this);
        }
    }
    public BuilderFactory(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        BuilderFactory factory = new BuilderFactory.Builder(1,1).calories(1).fat(1).sodium(1).carbohydrate(1).build();
    }
}
