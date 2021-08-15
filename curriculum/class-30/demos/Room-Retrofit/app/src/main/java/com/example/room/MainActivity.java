package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String FOOD_BASKET = "food-basket";
    private static final String TAG = "MainActivity";
    private FoodDao foodDao;
    private AppDatabase database;
    private int foodItemImage;

    private static HashMap<String, Integer> imageIconDatabase = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageIconDatabase.put("Apple", R.drawable.ic__01_apple);
        imageIconDatabase.put("Humus", R.drawable.ic__03_hummus);
        imageIconDatabase.put("Hamburger", R.drawable.ic_hamburger);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, FOOD_BASKET)
                .allowMainThreadQueries().build();
        foodDao = database.foodDao();

        Spinner spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.foods, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                foodItemImage = imageIconDatabase.get(text);
                Log.i(TAG, "onItemSelected: " + text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button addButton = findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inputName = findViewById(R.id.editItemName);
                EditText inputQuantity = findViewById(R.id.editTextQuantity);

                String name = inputName.getText().toString();
                int quantity = Integer.parseInt(inputQuantity.getText().toString());

                // save data
                FoodItem foodItem = new FoodItem(name, quantity);
                foodItem.setImage(foodItemImage);

                // saves the data to the local sqlite database
                // moved to the response method below
//                foodDao.insertOne(foodItem);

                // save the data to the API as well
                Call<FoodItem> call = RetrofitClient.getInstance().getMyApi().createFoodItem(foodItem);
                call.enqueue(new Callback<FoodItem>() {
                    @Override
                    public void onResponse(@NotNull Call<FoodItem> call, @NotNull Response<FoodItem> response) {
                        if (response.isSuccessful()) {
                            foodDao.insertOne(new FoodItem(
                                    response.body().getName(),
                                    response.body().getQuantity()
                            ));
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FoodItem> call, @NotNull Throwable t) {
                        Log.e(TAG, "onFailure: => " + t.toString());
                    }
                });

                Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_food) {
            Intent secondListIntent = new Intent(this, ShoppingListActivity.class);
            startActivity(secondListIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}