package com.dimaoprog.sportsconnectivity.foodViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dimaoprog.sportsconnectivity.databinding.ItemMealAddBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;

public class DetailFoodViewHolder extends RecyclerView.ViewHolder {

    private ItemMealAddBinding binding;

    public DetailFoodViewHolder(@NonNull ItemMealAddBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Meal meal) {
        binding.setMealItem(meal);
    }
}