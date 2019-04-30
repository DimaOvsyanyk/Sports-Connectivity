package com.dimaoprog.sportsconnectivity.foodViews;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.ForWorkoutsActivity;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFoodFragment extends Fragment {

    public static final String MENU_ID = "menu id";

    public static DetailFoodFragment newInstance(long menuId) {
        Bundle args = new Bundle();
        args.putLong(MENU_ID, menuId);
        DetailFoodFragment fragment = new DetailFoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Unbinder unbinder;

    @BindView(R.id.txt_title_menu_detail)
    TextView txtMenuTitleDetail;
    @BindView(R.id.txt_date_menu_detail)
    TextView txtMenuDateDetail;
    @BindView(R.id.rv_detail_meals)
    RecyclerView detailMeals;

    private DetailFoodViewModel dfViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_food, container, false);
        unbinder = ButterKnife.bind(this, v);
        dfViewModel = ViewModelProviders.of(this).get(DetailFoodViewModel.class);
        dfViewModel.setMenuId(getArguments().getLong(MENU_ID, -1));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailMeals.setLayoutManager(new LinearLayoutManager(getContext()));
        final DetailFoodAdapter detailFoodAdapter = new DetailFoodAdapter();
        detailMeals.setAdapter(detailFoodAdapter);
        dfViewModel.getMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable List<Meal> meals) {
                detailFoodAdapter.submitList(meals);
            }
        });
        fillViews(dfViewModel.getCurrentMenu());
    }

    private void fillViews(DailyMenu menu) {
        txtMenuTitleDetail.setText(menu.getMenuTitle());
        txtMenuDateDetail.setText(menu.getDateOfMenu());

    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
    }

}