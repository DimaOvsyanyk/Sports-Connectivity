package com.dimaoprog.sportsconnectivity.receiptViews;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.ItemMealdbBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;

public class ReceiptListAdapter extends ListAdapter<MealDB, ReceiptListViewHolder> {

    private FragmentNaviManager navigation;

    protected ReceiptListAdapter(FragmentNaviManager navigation) {
        super(DIFF_CALLBACK);
        this.navigation = navigation;
    }

    private static final DiffUtil.ItemCallback<MealDB> DIFF_CALLBACK = new DiffUtil.ItemCallback<MealDB>() {
        @Override
        public boolean areItemsTheSame(@NonNull MealDB mealDB, @NonNull MealDB t1) {
            return mealDB.getIdMeal().equals(t1.getIdMeal());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MealDB mealDB, @NonNull MealDB t1) {
            return mealDB.getStrMeal().equals(t1.getStrMeal());
        }
    };

    @NonNull
    @Override
    public ReceiptListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMealdbBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_mealdb, viewGroup, false);
        return new ReceiptListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptListViewHolder receiptListViewHolder, int i) {
        receiptListViewHolder.bind(getItem(i), navigation);
    }

}
