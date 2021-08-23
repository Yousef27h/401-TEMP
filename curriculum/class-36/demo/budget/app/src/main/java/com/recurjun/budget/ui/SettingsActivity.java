package com.recurjun.budget.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Account;
import com.amplifyframework.datastore.generated.model.Expense;
import com.recurjun.budget.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    private final List<Account> accounts = new ArrayList<>();
    private final List<String> spinnerData = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Handler handler = new Handler(Looper.getMainLooper(), message -> {
            arrayAdapter.notifyDataSetChanged();
            return false;
        });

        Amplify.API.query(ModelQuery.list(Account.class),
                success -> {
                    for (Account account : success.getData()) {
                        accounts.add(account);
                        spinnerData.add(account.getName());
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.i(TAG, "problem getting accounts: " + error.toString()));

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putString("accountName", accounts.get(i).getName());
                editor.putString("accountId", accounts.get(i).getId());
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Creating the ArrayAdapter instance having the country list
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerData);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(arrayAdapter);
        
        Button addAccount = findViewById(R.id.addButton);
        addAccount.setOnClickListener(view -> {
            EditText accountEditText = findViewById(R.id.account);
            EditText descriptionEditText = findViewById(R.id.description);

            String accountName = accountEditText.getText().toString();
            String accountDescription = descriptionEditText.getText().toString();

            Account account = Account.builder()
                    .name(accountName)
                    .description(accountDescription)
                    .build();

            Amplify.API.mutate(ModelMutation.create(account),
                    success -> {
                        Log.i(TAG, success.getData().getName() + "account created");
                        handler.sendEmptyMessage(1);
                        spinnerData.add(success.getData().getName());
                    },
                    error -> Log.e(TAG, "onClick: " + error.toString())
            );
        });
    }
}