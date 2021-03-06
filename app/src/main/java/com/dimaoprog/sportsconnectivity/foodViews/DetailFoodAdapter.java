package com.dimaoprog.sportsconnectivity.foodViews;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.ItemMealAddBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;

public class DetailFoodAdapter extends ListAdapter<Meal, DetailFoodViewHolder> {

    public DetailFoodAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Meal> DIFF_CALLBACK = new DiffUtil.ItemCallback<Meal>() {
        @Override
        public boolean areItemsTheSame(@NonNull Meal meal, @NonNull Meal t1) {
            return meal.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meal meal, @NonNull Meal t1) {
            return meal.getId() == t1.getId() &&
                    meal.getFoodIntake().equals(t1.getFoodIntake()) &&
                    meal.getMeal().equals(t1.getMeal());
        }
    };

    @NonNull
    @Override
    public DetailFoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMealAddBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_meal_add, viewGroup, false);
        return new DetailFoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailFoodViewHolder detailFoodViewHolder, int i) {
        detailFoodViewHolder.bind(getItem(i));
    }
}
