package com.recurjun.budget.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDAO {
    @Insert
    void insertOne(Expense expense);

    @Query("SELECT * FROM expense WHERE name LIKE :name")
    Expense findByName(String name);

    @Query("SELECT * FROM expense")
    List<Expense> findAll();

    @Delete
    void deleteItem(Expense expense);
}
