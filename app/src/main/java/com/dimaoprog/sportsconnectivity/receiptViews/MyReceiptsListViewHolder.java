package com.dimaoprog.sportsconnectivity.receiptViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.databinding.ItemMealdbBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;

public class MyReceiptsListViewHolder extends RecyclerView.ViewHolder {

    private ItemMealdbBinding binding;

    public MyReceiptsListViewHolder(@NonNull ItemMealdbBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(MealDBFavorite mealDBFavorite, FragmentNaviManager navigation) {
        binding.setMealDB(new MealDB(mealDBFavorite));
        itemView.setOnClickListener(__ -> navigation.showNewFragment(MyReceiptDetailFragment.newInstance(mealDBFavorite.getIdMeal())));
    }
}
