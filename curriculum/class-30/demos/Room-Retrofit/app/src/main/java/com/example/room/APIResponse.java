package com.example.room;

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
        private List<FoodItem> foods;

        public EmbeddedData() {
        }

        public List<FoodItem> getFoods() {
            return foods;
        }
    }
}
