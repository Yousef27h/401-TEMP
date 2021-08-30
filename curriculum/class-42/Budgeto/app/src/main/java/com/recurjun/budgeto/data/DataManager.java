package com.recurjun.budgeto.data;

import com.recurjun.budgeto.data.models.Account;
import com.recurjun.budgeto.data.models.Expense;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;

    private final List<Expense> expenseList = new ArrayList<>();
    private final List<Account> accountList = new ArrayList<>();

    private DataManager() {

    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }
}
