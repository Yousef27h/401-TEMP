package com.recurjun.budgeto.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.recurjun.budgeto.R;
import com.recurjun.budgeto.data.DataManager;
import com.recurjun.budgeto.data.models.Expense;
import com.recurjun.budgeto.ui.adapter.ExpenseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager.getInstance().getExpenseList().add(new Expense("Wash", "Dishes"));
        DataManager.getInstance().getExpenseList().add(new Expense("Cook", "Food"));
        DataManager.getInstance().getExpenseList().add(new Expense("Clean", "House"));

        RecyclerView recyclerView = findViewById(R.id.expenses);
        ExpenseListAdapter adapter = new ExpenseListAdapter(DataManager.getInstance().getExpenseList(),
                new ExpenseListAdapter.ExpenseClickListener() {
            @Override
            public void onExpenseClicked(int position, String action) {
                switch (action) {
                    case "details":
                        Intent detailsActivityIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                        detailsActivityIntent.putExtra("expenseId", position);
                        startActivity(detailsActivityIntent);
                        break;
                    case "delete":
                        DataManager.getInstance().getExpenseList().remove(position);

                    default:
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        MaterialButton accounts = findViewById(R.id.button_accounts);
        accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountsActivityIntent = new Intent(getApplicationContext(), AccountsActivity.class);
                startActivity(accountsActivityIntent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.regular_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}