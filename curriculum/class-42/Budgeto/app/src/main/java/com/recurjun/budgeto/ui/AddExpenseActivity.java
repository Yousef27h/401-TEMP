package com.recurjun.budgeto.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.recurjun.budgeto.R;
import com.recurjun.budgeto.data.models.Account;
import com.recurjun.budgeto.data.models.Expense;

public class AddExpenseActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SharedPreferences sharedPreferences;
    private Account account;
    private TextView accountPreference;
    private EditText expenseEditText;
    private EditText descriptionEditText;
    private EditText costEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            expenseEditText = findViewById(R.id.account);
            descriptionEditText = findViewById(R.id.description);
            costEditText = findViewById(R.id.cost);

            String expenseName = expenseEditText.getText().toString();
            String expenseDescription = descriptionEditText.getText().toString();
            String expenseCost = costEditText.getText().toString();

            if (expenseName.isEmpty() || expenseCost.isEmpty()) {
                Toast.makeText(
                        AddExpenseActivity.this,
                        "Please fill out the form",
                        Toast.LENGTH_SHORT).show();
            } else {
                // create a new expense
                Expense expenseItem = new Expense(expenseName, expenseDescription);

                // TODO: 8/30/21 add it to the data manager or your room db or your api
                // TODO: 8/30/21 find a way to use the location code to get the location and save in the object 
                // TODO: 8/30/21 place it on a map
                

//                if (isNetworkAvailable(getApplicationContext())) {
//
//                    Log.i(TAG, "onClick: the network is available");
//                    Amplify.API.mutate(ModelMutation.create(Account.builder().name("Savings").description("Savings Account").build()),
//                            success -> Log.i(TAG, "Saved item: " + success.getData()),
//                            error -> Log.e(TAG, "Could not save item to API/dynamodb", error));
//
//                    saveExpenseToAPI(expenseItem);
//
//                    Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.i(TAG, "onClick: net down");
//                    Amplify.DataStore.save(expenseItem,
//                            success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
//                            error -> Log.e("Tutorial", "Could not save item to DataStore", error)
//                    );
//                }
            }
        });

        Button clearForm = findViewById(R.id.clear_form);
        clearForm.setOnClickListener(view -> {
            expenseEditText.setText("");
            expenseEditText.requestFocus();
            descriptionEditText.setText("");
            costEditText.setText("");
        });
    }
}