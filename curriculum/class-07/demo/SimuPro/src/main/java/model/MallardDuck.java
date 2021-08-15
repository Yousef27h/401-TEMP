package model;

import behaviours.Eat;
import behaviours.Quacking;
import behaviours.Swim;

public class MallardDuck extends Duck implements Eat, Quacking, Swim {
    public MallardDuck(String type, String color) {
        super(type, color);
    }


    @Override
    public void eat() {
        System.out.println("i am messy eater");
    }

    @Override
    public void quack() {
        System.out.println("my quack is very annoying");
    }

    @Override
    public void swim() {
        System.out.println("better than mike phelps");
    }
}
