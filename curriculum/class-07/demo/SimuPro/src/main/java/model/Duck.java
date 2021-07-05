package model;

/**
 * Duck class for the simulation software
 */
public class Duck {
    private final String type;
    private final String color;
    private final int age;

    public Duck(String type, String color) {
        this.type = type;
        this.color = color;
        age = 5;
    }

    public void displayType() {
        System.out.println("My type is: " + type);
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }
}
