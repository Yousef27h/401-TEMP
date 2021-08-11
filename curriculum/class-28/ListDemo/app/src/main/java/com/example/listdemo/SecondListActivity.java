package com.example.listdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SecondListActivity extends AppCompatActivity {

    public static final String FOOD_NAME = "food_name";
    private static final String TAG = "SecondListActivity";
    private List<FoodItem> foodList;
    private FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_list);

        RecyclerView foodRecyclerView = findViewById(R.id.list2);

        // can be pulled from the network or a local database
        foodList = new ArrayList<>();
        foodList.add(new FoodItem("Apples"));
        foodList.add(new FoodItem("Cheese"));
        foodList.add(new FoodItem("Corn Flakes"));
        foodList.add(new FoodItem("Juice"));
        foodList.add(new FoodItem("Water"));
        foodList.add(new FoodItem("Humus"));
        foodList.add(new FoodItem("Chips"));
        foodList.add(new FoodItem("Chicken"));
        foodList.add(new FoodItem("Beef"));
        foodList.add(new FoodItem("Lamb"));
        foodList.add(new FoodItem("Fish"));
        foodList.add(new FoodItem("Shrimp"));
        foodList.add(new FoodItem("Oyster"));
        foodList.add(new FoodItem("Lobster"));
        foodList.add(new FoodItem("Garlic"));
        foodList.add(new FoodItem("Butter"));
        foodList.add(new FoodItem("Lemon"));

        adapter = new FoodAdapter(foodList, new FoodAdapter.OnFoodItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent goToDetailsIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                goToDetailsIntent.putExtra(FOOD_NAME, foodList.get(position).getName());
                startActivity(goToDetailsIntent);
            }

            @Override
            public void onDeleteItem(int position) {
                foodList.remove(position);
                Log.i(TAG, "onDeleteItem: our list =>>>> " + foodList.toString());
                listItemDeleted();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);

        foodRecyclerView.setLayoutManager(linearLayoutManager);
        foodRecyclerView.setAdapter(adapter);
    }

    private void listItemDeleted() {
        adapter.notifyDataSetChanged();
    }
}