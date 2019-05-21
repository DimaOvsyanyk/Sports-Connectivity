package com.dimaoprog.sportsconnectivity.receiptViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentMyReceiptsBinding;

public class MyReceiptsFragment extends Fragment {

    private MyReceiptsListAdapter.IFavoriteReceiptPickedListener iFavoriteReceiptPickedListener;

    public static MyReceiptsFragment newInstance(MyReceiptsListAdapter.IFavoriteReceiptPickedListener iFavoriteReceiptPickedListener,
                                                 WantToAddNewReceiptListener wantToAddNewReceiptListener) {
        MyReceiptsFragment fragment = new MyReceiptsFragment();
        fragment.setIFavoriteReceiptPickedListener(iFavoriteReceiptPickedListener);
        fragment.setWantToAddNewReceiptListener(wantToAddNewReceiptListener);
        return fragment;
    }

    public void setIFavoriteReceiptPickedListener(MyReceiptsListAdapter.IFavoriteReceiptPickedListener iFavoriteReceiptPickedListener) {
        this.iFavoriteReceiptPickedListener = iFavoriteReceiptPickedListener;
    }

    private WantToAddNewReceiptListener wantToAddNewReceiptListener;

    public interface WantToAddNewReceiptListener {
        void openReceiptAddNewFragment();
    }

    public void setWantToAddNewReceiptListener(WantToAddNewReceiptListener wantToAddNewReceiptListener) {
        this.wantToAddNewReceiptListener = wantToAddNewReceiptListener;
    }

    private FragmentMyReceiptsBinding binding;
    private MyReceiptsViewModel myrViewModel;
    private MyReceiptsListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_receipts, container, false);
        myrViewModel = ViewModelProviders.of(this).get(MyReceiptsViewModel.class);
        setupRecyclerView();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                myrViewModel.deleteReceipt(adapter.getMealAtPos(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(binding.rvMyReceipts);

        binding.fabAddReceipt.setOnClickListener(__ -> wantToAddNewReceiptListener.openReceiptAddNewFragment());
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        adapter = new MyReceiptsListAdapter(iFavoriteReceiptPickedListener);
        binding.rvMyReceipts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMyReceipts.setAdapter(adapter);
        myrViewModel.getMealDBFavoritesList().observe(this, mealDBFavorites -> adapter.submitList(mealDBFavorites));
    }

}
