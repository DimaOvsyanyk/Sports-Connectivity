package com.dimaoprog.sportsconnectivity.foodViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dimaoprog.sportsconnectivity.databinding.ItemDayMenuBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    private ItemDayMenuBinding binding;

    public FoodViewHolder(@NonNull ItemDayMenuBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(final DailyMenu dailyMenu, final FoodListAdapter.DetailFoodListener detailFoodListener) {
        binding.setDailyMenu(dailyMenu);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailFoodListener.openDetailFoodFragment(dailyMenu.getId());
            }
        });
    }
}
