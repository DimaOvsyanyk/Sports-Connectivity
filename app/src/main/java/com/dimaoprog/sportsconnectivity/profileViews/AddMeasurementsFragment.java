package com.dimaoprog.sportsconnectivity.profileViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentAddMeasurementsBinding;

import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;

public class AddMeasurementsFragment extends Fragment {

    AddMeasurementsViewModel amViewModel;
    FragmentAddMeasurementsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_measurements, container, false);
        View v = binding.getRoot();
        amViewModel = ViewModelProviders.of(this).get(AddMeasurementsViewModel.class);

        binding.setAddModel(amViewModel);
        return v;
    }

}
