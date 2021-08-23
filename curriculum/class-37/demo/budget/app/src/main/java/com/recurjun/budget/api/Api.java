package com.recurjun.budget.api;

import com.recurjun.budget.data.APIResponse;
import com.recurjun.budget.data.Expense;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "http://192.168.1.101:8080/";

    @GET("expenses")
    Call<APIResponse> getExpenses();

    @POST("expenses")
    Call<Expense> createExpense(@Body Expense expense);
}
