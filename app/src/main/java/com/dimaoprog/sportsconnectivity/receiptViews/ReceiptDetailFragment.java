package com.dimaoprog.sportsconnectivity.receiptViews;

import android.app.ProgressDialog;
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

    private ReceiptDetailViewModel rdViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getArguments().getString(MEAL_DB_ID);
        rdViewModel = ViewModelProviders.of(this).get(ReceiptDetailViewModel.class);
        rdViewModel.setCurrentMealById(id);
    }

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentReceiptDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt_detail, container, false);
        setupProgressDialog();
        rdViewModel.getShowDialogLive().observe(this, show -> showProgressDialog(show));
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

    private void showProgressDialog(boolean show) {
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Receiving receipt details");
        progressDialog.setMessage("Please wait");
    }
}
