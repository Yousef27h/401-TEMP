package model;

import behaviours.Eat;

public class RedheadDuck extends Duck implements Eat {
    public RedheadDuck(String type, String color) {
        super(type, color);
    }

    @Override
    public void eat() {
        System.out.println("i eat with grace");
    }
}
