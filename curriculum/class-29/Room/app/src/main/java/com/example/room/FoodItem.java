package com.example.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FoodItem {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "food_name")
    private final String name;

    @ColumnInfo(name = "food_quantity")
    private final int quantity;

    @ColumnInfo(name = "food_image")
    private int image;

    public FoodItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImage() {
        return image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
