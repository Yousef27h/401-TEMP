package com.recurjun.budget.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Expense;
import com.recurjun.budget.R;

import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

    private static final String TAG = "FoodAdapter";
    private final List<Expense> expenses;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClicked(int position);
        void onDeleteItem(int position);
    }

    public ExpenseListAdapter(List<Expense> expenses, OnItemClickListener listener) {
        this.expenses = expenses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_expense, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListAdapter.ViewHolder holder, int position) {
        Expense item = expenses.get(position);
        holder.expense.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView expense;
        private TextView delete;

        ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            image = itemView.findViewById(R.id.food_image);
            expense = itemView.findViewById(R.id.food_name);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(getAdapterPosition());
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteItem(getAdapterPosition());
                }
            });
        }
    }
}
