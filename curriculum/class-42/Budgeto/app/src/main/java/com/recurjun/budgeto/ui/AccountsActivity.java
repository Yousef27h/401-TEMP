package com.recurjun.budgeto.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.recurjun.budgeto.R;
import com.recurjun.budgeto.data.DataManager;
import com.recurjun.budgeto.data.models.Account;
import com.recurjun.budgeto.ui.adapter.AccountsListAdapter;

import java.util.Objects;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DataManager.getInstance().getAccountList().add(new Account("Savings"));
        DataManager.getInstance().getAccountList().add(new Account("Chequing"));
        DataManager.getInstance().getAccountList().add(new Account("Bonds"));

        RecyclerView recyclerView = findViewById(R.id.accounts);

        AccountsListAdapter adapter = new AccountsListAdapter(DataManager.getInstance().getAccountList(),
                new AccountsListAdapter.AccountClickListener() {
            @Override
            public void onAccountClicked(int position, String action) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.regular_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAccountActivityIntent = new Intent(getApplicationContext(), AddAccountActivity.class);
                startActivity(addAccountActivityIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}