package com.recurjun.budget;

import com.recurjun.budget.api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Singleton pattern - Google it
public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Api myApi;

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }

        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}
