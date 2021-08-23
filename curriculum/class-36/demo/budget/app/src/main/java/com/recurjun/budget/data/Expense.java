package com.recurjun.budget.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;
    private String description;
    private Double cost;

    public Expense() {
    }

    public Expense(String name, Double cost) {
        this.name = name;
        this.cost = cost;
        this.description = "No description";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
