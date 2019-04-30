package com.dimaoprog.sportsconnectivity.foodViews;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;

public class FoodListAdapter extends ListAdapter<DailyMenu, FoodViewHolder> {

    DetailFoodListener detailFoodListener;

    public interface DetailFoodListener {
        void openDetailFoodFragment(long workoutId);
    }

    protected FoodListAdapter(DetailFoodListener detailFoodListener) {
        super(DIFF_CALLBACK);
        this.detailFoodListener = detailFoodListener;
    }

    private static final DiffUtil.ItemCallback<DailyMenu> DIFF_CALLBACK = new DiffUtil.ItemCallback<DailyMenu>() {
        @Override
        public boolean areItemsTheSame(@NonNull DailyMenu dailyMenu, @NonNull DailyMenu t1) {
            return dailyMenu.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull DailyMenu dailyMenu, @NonNull DailyMenu t1) {
            return dailyMenu.getMenuTitle().equals(t1.getMenuTitle()) &&
                    dailyMenu.getDateOfMenu().equals(t1.getDateOfMenu());
        }
    };

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_day_menu, viewGroup, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {
        foodViewHolder.bind(getItem(i), detailFoodListener);
    }

    public DailyMenu getMenuAtPos(int position) {
        return getItem(position);
    }
}
