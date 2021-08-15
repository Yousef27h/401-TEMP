package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        String name = intent.getExtras().getString(ShoppingListActivity.FOOD_NAME);
        TextView nameTextView = findViewById(R.id.food_name_details);
        nameTextView.setText(name);

        Log.i(TAG, "onCreate: the data is >>> " + name);
    }
}