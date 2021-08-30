package com.recurjun.budgeto.data.models;

public class Expense {

    private String name;
    private String description;

    private Double latitude;
    private Double longitude;

    public Expense() {
    }

    public Expense(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Expense(String name, String description, Double latitude, Double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
