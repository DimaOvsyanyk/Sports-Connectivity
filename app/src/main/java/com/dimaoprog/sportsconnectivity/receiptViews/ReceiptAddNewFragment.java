package com.dimaoprog.sportsconnectivity.receiptViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentReceiptAddNewBinding;

public class ReceiptAddNewFragment extends Fragment {

    public static ReceiptAddNewFragment newInstance(ReceiptListAdapter.IReceiptPickedListener iReceiptPickedListener) {

        Bundle args = new Bundle();

        ReceiptAddNewFragment fragment = new ReceiptAddNewFragment();
        fragment.setIReceiptPickedListener(iReceiptPickedListener);
        fragment.setArguments(args);
        return fragment;
    }

    private ReceiptListAdapter.IReceiptPickedListener iReceiptPickedListener;

    public void setIReceiptPickedListener(ReceiptListAdapter.IReceiptPickedListener iReceiptPickedListener) {
        this.iReceiptPickedListener = iReceiptPickedListener;
    }

    private FragmentReceiptAddNewBinding binding;
    private ReceiptAddNewViewModel ranViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt_add_new, container, false);
        ranViewModel = ViewModelProviders.of(this).get(ReceiptAddNewViewModel.class);
        setupRecyclerView();
        setupSpinner();
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        ReceiptListAdapter adapter = new ReceiptListAdapter(iReceiptPickedListener);
        binding.rvReceipts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvReceipts.setAdapter(adapter);
        ranViewModel.getMealDBLiveList().observe(this, mealDBList -> adapter.submitList(mealDBList));
    }

    private void setupSpinner() {
        String[] strMealCategories = getResources().getStringArray(R.array.receipt_categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strMealCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMealCategories.setAdapter(adapter);
        binding.spinnerMealCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ranViewModel.setNewListByCategory(strMealCategories[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
