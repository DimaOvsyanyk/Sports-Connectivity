package com.dimaoprog.sportsconnectivity.foodViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFoodViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_meal_title_item)
    TextView txtMealTitleItem;
    @BindView(R.id.txt_meal_description_item)
    TextView txtMealDescriptionItem;
    @BindView(R.id.btn_add_meal_item)
    Button btnAddMeal;

    public DetailFoodViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Meal meal) {
        txtMealTitleItem.setText(meal.getFoodIntake());
        txtMealDescriptionItem.setText(meal.getMeal());
        btnAddMeal.setVisibility(View.INVISIBLE);
    }


}
