package com.dimaoprog.sportsconnectivity.receiptViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentReceiptDetailBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;

import java.util.Objects;

public class MyReceiptDetailFragment extends Fragment {

    private static final String MEAL_DB_ID = "mealDBid";

    public static MyReceiptDetailFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(MEAL_DB_ID, id);
        MyReceiptDetailFragment fragment = new MyReceiptDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private MyReceiptDetailViewModel myrdViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = Objects.requireNonNull(getArguments()).getLong(MEAL_DB_ID);
        myrdViewModel = ViewModelProviders.of(this).get(MyReceiptDetailViewModel.class);
        myrdViewModel.setCurrentMealDBFavorite(id);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentReceiptDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt_detail, container, false);
        binding.setMealDB(new MealDB(myrdViewModel.getCurrentMealDBFavorite()));
        binding.btnAddToFavorites.setVisibility(View.GONE);
        return binding.getRoot();
    }

}
