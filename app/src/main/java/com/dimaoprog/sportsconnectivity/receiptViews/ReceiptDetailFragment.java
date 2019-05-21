package com.dimaoprog.sportsconnectivity.receiptViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentReceiptDetailBinding;

public class ReceiptDetailFragment extends Fragment {

    private static final String MEAL_DB_ID = "mealDBid";

    public static ReceiptDetailFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(MEAL_DB_ID, id);
        ReceiptDetailFragment fragment = new ReceiptDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ReceiptDetailViewModel rdViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getArguments().getString(MEAL_DB_ID);
        rdViewModel = ViewModelProviders.of(this).get(ReceiptDetailViewModel.class);
        rdViewModel.setCurrentMealById(id);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentReceiptDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt_detail, container, false);
        rdViewModel.getCurrentMealDBLive().observe(this, mealDB -> binding.setMealDB(mealDB));
        binding.btnAddToFavorites.setOnClickListener(__ -> {
            if (rdViewModel.saveMealDBtoFavorite()) {
                Toast.makeText(getContext(), "Receipt " + rdViewModel.getCurrentMealDB().getStrMeal() +
                        " added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Receipt " + rdViewModel.getCurrentMealDB().getStrMeal() +
                        " already in your favorites", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

}
