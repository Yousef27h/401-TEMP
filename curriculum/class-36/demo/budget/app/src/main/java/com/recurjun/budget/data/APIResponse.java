package com.recurjun.budget.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse {

    @SerializedName("_embedded")
    private EmbeddedData data;

    public EmbeddedData getData() {
        return data;
    }

    public APIResponse() {
    }

    public static final class EmbeddedData {
        private List<Expense> expenses;

        public EmbeddedData() {
        }

        public List<Expense> getExpenses() {
            return expenses;
        }
    }
}
