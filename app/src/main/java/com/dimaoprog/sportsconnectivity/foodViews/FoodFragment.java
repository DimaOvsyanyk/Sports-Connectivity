package com.dimaoprog.sportsconnectivity.foodViews;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodFragment extends Fragment {

    FoodListAdapter.DetailFoodListener detailFoodListener;

    public static FoodFragment newInstance(FoodListAdapter.DetailFoodListener detailFoodListener) {
        FoodFragment fragment = new FoodFragment();
        fragment.setDetailFoodListener(detailFoodListener);
        return fragment;
    }

    public void setDetailFoodListener(FoodListAdapter.DetailFoodListener detailFoodListener) {
        this.detailFoodListener = detailFoodListener;
    }

    private FoodViewModel fViewModel;

    Unbinder unbinder;
    @BindView(R.id.rv_food_list)
    RecyclerView foodList;
    @BindView(R.id.swipe_layout_food)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food, container, false);
        unbinder = ButterKnife.bind(this, v);
        foodList.setLayoutManager(new LinearLayoutManager(getContext()));
        final FoodListAdapter foodAdapter = new FoodListAdapter(detailFoodListener);
        foodList.setAdapter(foodAdapter);

        fViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        fViewModel.getAllMenu().observe(this, new Observer<List<DailyMenu>>() {
            @Override
            public void onChanged(@Nullable List<DailyMenu> dailyMenus) {
                foodAdapter.submitList(dailyMenus);
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    fViewModel.addWeekMenuFromJson(getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                swipeRefresh.setRefreshing(false);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                fViewModel.deleteMenu(foodAdapter.getMenuAtPos(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(foodList);


        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
