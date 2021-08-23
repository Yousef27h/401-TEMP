package com.recurjun.budget.data;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDataManager {
    private static ExpenseDataManager instance = null;
    private List<com.amplifyframework.datastore.generated.model.Expense> expenses = new ArrayList<>();

    private ExpenseDataManager() {
    }

    public static ExpenseDataManager getInstance() {
        if (instance == null) {
            instance = new ExpenseDataManager();
        }

        return instance;
    }

    public void setData(List<com.amplifyframework.datastore.generated.model.Expense> data) {
        expenses = data;
    }

    public List<com.amplifyframework.datastore.generated.model.Expense> getData() {
        return expenses;
    }
}
