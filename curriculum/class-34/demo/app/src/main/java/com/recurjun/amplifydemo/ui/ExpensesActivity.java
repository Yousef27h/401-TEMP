package com.recurjun.amplifydemo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Expense;
import com.recurjun.amplifydemo.R;
import com.recurjun.amplifydemo.data.ExpenseDataManager;
import com.recurjun.amplifydemo.ui.adapter.ExpenseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExpensesActivity extends AppCompatActivity {
    public static final String EXPENSE = "expense";
    private static final String TAG = "ExpenseActivity";
    private ExpenseListAdapter adapter;
    private final List<Expense> data = ExpenseDataManager.getInstance().getData();
    private RecyclerView expensesList;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

//        getExpenseDataFromAPI();

        handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
                        expensesList.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });

        expensesList = findViewById(R.id.expenses);
        adapter = new ExpenseListAdapter(data,
                new ExpenseListAdapter.OnExpenseItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        Intent detailsIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                        detailsIntent.putExtra(EXPENSE, data.get(position).getName());
                        startActivity(detailsIntent);
                    }

                    @Override
                    public void onDeleteItem(int position) {
                        data.remove(position); // deletes from the in memory array list

                        // TODO: 8/18/21 delete from Amplify API
                        // TODO: 8/18/21 delete from any local storage/cache if any

                        dataSetChanged();
                    }
                });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);

        expensesList.setLayoutManager(linearLayoutManager);
        expensesList.setAdapter(adapter);
    }

    private void dataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    private void getExpenseDataFromAPI() {
        Amplify.API.query(ModelQuery.list(Expense.class),
                response -> {
                    for (Expense expense : response.getData()) {
                        data.add(expense);
                        Log.i(TAG, "onCreate: the expenses are => " + expense.getName());
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e(TAG, "onCreate: Failed to get expenses => " + error.toString())
        );
    }
}