package model;

import behaviours.Squeaking;

public class RubberDuck extends Duck implements Squeaking {
    public RubberDuck(String type, String color) {
        super(type, color);
    }

    @Override
    public void squeak() {
        System.out.println("I like to squeak");
    }
}
