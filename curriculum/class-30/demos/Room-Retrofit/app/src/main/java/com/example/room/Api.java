package com.example.room;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://192.168.1.101:8080/";

    @GET("foods")
    Call<APIResponse> getFoodItems();

    @POST("foods")
    Call<FoodItem> createFoodItem(@Body FoodItem foodItem);
}
