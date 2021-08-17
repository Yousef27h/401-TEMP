package com.recurjun.budget.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.recurjun.budget.R;
import com.recurjun.budget.data.Expense;
import com.recurjun.budget.ui.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExpensesActivity extends AppCompatActivity {
    public static final String EXPENSE = "expense";
    private List<Expense> data;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        data = loadDataFromCode();

        RecyclerView expensesList = findViewById(R.id.expenses);
        adapter = new ListAdapter(data,
                new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent detailsIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                detailsIntent.putExtra(EXPENSE, data.get(position).getName());
                startActivity(detailsIntent);
            }

            @Override
            public void onDeleteItem(int position) {
                data.remove(position);
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

    private List<Expense> loadDataFromCode() {
        List<Expense> expenses = new ArrayList<>();

        for (int index = 1; index <= 10; index++) {
            expenses.add(new Expense("Expense " + index, 100.00 * index));
        }

        return expenses;
    }
}