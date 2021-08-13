package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingListActivity extends AppCompatActivity {

    public static final String FOOD_NAME = "food_name";
    private static final String TAG = "SecondListActivity";
    private List<FoodItem> foodList = new ArrayList<>();
    private FoodAdapter adapter;

    private FoodDao foodDao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppin_list);

//        List<FoodItem> foodItems = new ArrayList<>();
//        foodItems.add(new FoodItem("Apples", 20));
//        foodItems.add(new FoodItem("Humus", 5));
//        foodItems.add(new FoodItem("Falafel", 5));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, MainActivity.FOOD_BASKET).allowMainThreadQueries().build();

        // can be pulled from the network or a local database
//        foodDao = db.foodDao();
//        foodList = foodDao.findAll();

        getFoodItems();

        RecyclerView recyclerView = findViewById(R.id.list);
        adapter = new FoodAdapter(foodList, new FoodAdapter.OnFoodItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent goToDetailsIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                goToDetailsIntent.putExtra(FOOD_NAME, foodList.get(position).getName());
                startActivity(goToDetailsIntent);
            }

            @Override
            public void onDeleteItem(int position) {
                foodDao.deleteItem(foodList.get(position));
                foodList.remove(position);
                notifyDatasetChanged();
                Toast.makeText(ShoppingListActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpdateItem(int position) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void notifyDatasetChanged() {
        adapter.notifyDataSetChanged();
    }

    /**
     * Gets food items from the API
     */
    private void getFoodItems() {
        Call<APIResponse> call = RetrofitClient.getInstance().getMyApi().getFoodItems();
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NotNull Call<APIResponse> call, @NotNull Response<APIResponse> response) {
                if (response.isSuccessful() && response.body().getData().getFoods() != null) {
                    Log.i(TAG, "onResponse: => " + response.body());
                    APIResponse APIResponse = response.body();
                    APIResponse.EmbeddedData embeddedData = APIResponse.getData();
                    Log.i(TAG, "onResponse: => " + embeddedData.getFoods());

                    foodList.addAll(embeddedData.getFoods());
                    notifyDatasetChanged();
                }
            }

            @Override
            public void onFailure(@NotNull Call<APIResponse> call, @NotNull Throwable t) {
                Log.i(TAG, "onFailure: called => " + t.toString());
            }
        });
    }
}