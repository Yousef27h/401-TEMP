package com.recurjun.budgeto.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recurjun.budgeto.R;
import com.recurjun.budgeto.data.models.Expense;

import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {
    private static final String TAG = "ExpenseListAdapter";

    private final ExpenseClickListener clickListener;
    private final List<Expense> expenseList;

    public interface ExpenseClickListener {
        void onExpenseClicked(int position, String action);
    }

    public ExpenseListAdapter(List<Expense> expenseList, ExpenseClickListener clickListener) {
        this.expenseList = expenseList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ExpenseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_expense, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense item = expenseList.get(position);
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView delete;

        public ViewHolder(@NonNull View itemView, ExpenseClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onExpenseClicked(getAdapterPosition(), "details");
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onExpenseClicked(getAdapterPosition(), "delete");
                }
            });
        }
    }
}
