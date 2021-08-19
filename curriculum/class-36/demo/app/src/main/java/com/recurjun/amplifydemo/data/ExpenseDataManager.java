package com.recurjun.amplifydemo.data;

import com.amplifyframework.datastore.generated.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDataManager {

    private static ExpenseDataManager instance = null;
    private List<Expense> expenses = new ArrayList<>();

    private ExpenseDataManager() {
    }

    public static ExpenseDataManager getInstance() {
        if (instance == null) {
            instance = new ExpenseDataManager();
        }

        return instance;
    }

    public void setData(List<Expense> data) {
        expenses = data;
    }

    public List<Expense> getData() {
        return expenses;
    }
}
