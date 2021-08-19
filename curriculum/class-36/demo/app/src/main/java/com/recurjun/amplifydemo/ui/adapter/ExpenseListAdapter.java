package com.recurjun.amplifydemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Expense;
import com.recurjun.amplifydemo.R;

import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

    private static final String TAG = "ExpenseListAdapter";
    private final List<Expense> expenses;
    private OnExpenseItemClickListener listener;

    public interface OnExpenseItemClickListener {
        void onItemClicked(int position);
        void onDeleteItem(int position);
    }

    public ExpenseListAdapter(List<Expense> expenses, OnExpenseItemClickListener listener) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

        private OnExpenseItemClickListener listener;

        private final View.OnClickListener cardListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(getAdapterPosition());
            }
        };

        private final View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteItem(getAdapterPosition());
            }
        };

        public ViewHolder(@NonNull View itemView, OnExpenseItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            image = itemView.findViewById(R.id.expense_image);
            expense = itemView.findViewById(R.id.expense_name);

            delete = itemView.findViewById(R.id.delete_textView);
            delete.setOnClickListener(deleteListener);

            itemView.setOnClickListener(cardListener);
        }
    }
}
