package com.recurjun.budgeto.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recurjun.budgeto.R;
import com.recurjun.budgeto.data.models.Account;
import com.recurjun.budgeto.data.models.Expense;

import java.util.List;

public class AccountsListAdapter extends RecyclerView.Adapter<AccountsListAdapter.ViewHolder> {

    private final AccountClickListener clickListener;
    private final List<Account> accountList;

    public interface AccountClickListener {
        void onAccountClicked(int position, String action);
    }

    public AccountsListAdapter(List<Account> accountList, AccountClickListener clickListener) {
        this.accountList = accountList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_account, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account item = accountList.get(position);
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView delete;

        public ViewHolder(@NonNull View itemView, AccountClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onAccountClicked(getAdapterPosition(), "details");
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onAccountClicked(getAdapterPosition(), "delete");
                }
            });
        }
    }
}
