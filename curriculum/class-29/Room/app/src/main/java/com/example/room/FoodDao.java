package com.example.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert
    void insertOne(FoodItem foodItem);

    @Query("SELECT * FROM foodItem WHERE food_name LIKE :name")
    FoodItem findByName(String name);

    @Query("SELECT * FROM foodItem")
    List<FoodItem> findAll();

    @Delete
    void deleteItem(FoodItem foodItem);
}
