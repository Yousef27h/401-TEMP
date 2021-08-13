package com.example.room;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private static final String TAG = "FoodAdapter";
    private final List<FoodItem> foodItems;
    private OnFoodItemClickListener listener;

    public interface OnFoodItemClickListener {
        void onItemClicked(int position);
        void onDeleteItem(int position);
        void onUpdateItem(int position);
    }

    public FoodAdapter(List<FoodItem> foodItems, OnFoodItemClickListener listener) {
        this.foodItems = foodItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = foodItems.get(position);
        holder.foodName.setText(item.getName());
        holder.foodQuantity.setText(String.valueOf(item.getQuantity()));

        if (item.getImage() == 0) {
            holder.foodImage.setImageResource(R.drawable.ic__04_picture);
        } else {
            holder.foodImage.setImageResource(item.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodImage;
        private TextView foodName;
        private TextView foodQuantity;
        private TextView delete;

        ViewHolder(@NonNull View itemView, OnFoodItemClickListener listener) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            foodName = itemView.findViewById(R.id.food_name);
            foodQuantity = itemView.findViewById(R.id.food_quantity);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: called");
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
