package com.dimaoprog.sportsconnectivity.foodViews;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.databinding.DialogAddMenuBinding;
import com.dimaoprog.sportsconnectivity.databinding.FragmentFoodBinding;

import org.json.JSONException;

import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

public class FoodFragment extends Fragment {

    @Inject
    FragmentNaviManager navigation;
    private FoodViewModel fViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentFoodBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_food, container, false);
        AppComponentBuild.getComponent().inject(this);

        binding.rvFoodList.setLayoutManager(new LinearLayoutManager(getContext()));
        final FoodListAdapter foodAdapter = new FoodListAdapter(navigation);
        binding.rvFoodList.setAdapter(foodAdapter);

        fViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        fViewModel.getAllMenu().observe(this, dailyMenus -> foodAdapter.submitList(dailyMenus));
        binding.swipeLayoutFood.setOnRefreshListener(() -> {
            try {
                fViewModel.addWeekMenuFromJson(getContext());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            binding.swipeLayoutFood.setRefreshing(false);
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
        }).attachToRecyclerView(binding.rvFoodList);

        binding.fabAddFood.setOnClickListener(__ -> showAddMenuDialog());
        return binding.getRoot();
    }

    public void showAddMenuDialog() {
        final Dialog dialogAddMenu = new Dialog(Objects.requireNonNull(getContext()));
        dialogAddMenu.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogAddMenuBinding bindingAddMenu = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_add_menu, null, false);
        dialogAddMenu.setContentView(bindingAddMenu.getRoot());
        dialogAddMenu.setCanceledOnTouchOutside(true);
        bindingAddMenu.setFoodModel(fViewModel);
        String[] foodIntakes = getResources().getStringArray(R.array.food_intakes);
        String[] daysOfWeek = getResources().getStringArray(R.array.days_of_week);

        bindingAddMenu.btnAddMenu.setOnClickListener(__ -> {
            if (fViewModel.isMenuOk()) {
                fViewModel.addNewMenu(foodIntakes, daysOfWeek);
                dialogAddMenu.dismiss();
            } else {
                Toast.makeText(getContext(), "You have empty fields", Toast.LENGTH_SHORT).show();
            }
        });
        dialogAddMenu.show();
    }
}
