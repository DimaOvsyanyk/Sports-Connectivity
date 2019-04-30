package com.dimaoprog.sportsconnectivity.foodViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_menu_title)
    TextView txtMenuTitle;
    @BindView(R.id.txt_menu_date)
    TextView txtMenuDate;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final DailyMenu dailyMenu, final FoodListAdapter.DetailFoodListener detailFoodListener) {
        txtMenuTitle.setText(dailyMenu.getMenuTitle());
        txtMenuDate.setText(dailyMenu.getDateOfMenu());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailFoodListener.openDetailFoodFragment(dailyMenu.getId());
            }
        });
    }
}
