package com.dimaoprog.sportsconnectivity.profileViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentAddMeasurementsBinding;

public class AddMeasurementsFragment extends Fragment {

    AddMeasurementsViewModel amViewModel;
    FragmentAddMeasurementsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_measurements, container, false);
        View v = binding.getRoot();
        amViewModel = ViewModelProviders.of(this).get(AddMeasurementsViewModel.class);

        binding.setAddModel(amViewModel);
        return v;
    }

}
