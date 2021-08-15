package com.example.listdemo;

public class FoodItem {

    private final String name;
    private int image;

    public FoodItem(String name) {
        this.name = name;
    }

    public FoodItem(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
