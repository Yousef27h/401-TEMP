package com.recurjun.budget.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.recurjun.budget.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String description = intent.getExtras().getString(ExpensesActivity.EXPENSE);

        TextView descriptionTextView = findViewById(R.id.description);
        descriptionTextView.setText(description);
    }
}