package com.dimaoprog.sportsconnectivity.foodViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.databinding.ItemDayMenuBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    private ItemDayMenuBinding binding;

    public FoodViewHolder(@NonNull ItemDayMenuBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(final DailyMenu dailyMenu, FragmentNaviManager navigation) {
        binding.setDailyMenu(dailyMenu);
        itemView.setOnClickListener(__ -> navigation.showNewFragment(DetailFoodFragment.newInstance(dailyMenu.getId())));
    }
}
