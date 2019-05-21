package com.dimaoprog.sportsconnectivity.receiptViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.dimaoprog.sportsconnectivity.databinding.ItemMealdbBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;

public class ReceiptListViewHolder extends RecyclerView.ViewHolder {

    private ItemMealdbBinding binding;

    public ReceiptListViewHolder(@NonNull ItemMealdbBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(MealDB mealDB, ReceiptListAdapter.IReceiptPickedListener iReceiptPickedListener) {
        binding.setMealDB(mealDB);
        itemView.setOnClickListener(__ -> iReceiptPickedListener.openReceiptDetailFragment(mealDB.getIdMeal()));
    }
}
