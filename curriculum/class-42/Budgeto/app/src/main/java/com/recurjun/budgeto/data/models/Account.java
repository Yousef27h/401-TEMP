package com.recurjun.budgeto.data.models;

public class Account {

    private String name;
    private String description;
    private Double balance;

    public Account() {
    }

    public Account(String name) {
        this.name = name;
        this.balance = 100.00;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getBalance() {
        return balance;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
