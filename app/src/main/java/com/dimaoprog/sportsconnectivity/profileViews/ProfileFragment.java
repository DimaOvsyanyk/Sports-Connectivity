package com.dimaoprog.sportsconnectivity.profileViews;

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
import com.dimaoprog.sportsconnectivity.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    ProfileActionListener profileActionListener;

    public static ProfileFragment newInstance(ProfileActionListener profileActionListener) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setProfileActionListener(profileActionListener);
        return fragment;
    }

    public void setProfileActionListener(ProfileActionListener profileActionListener) {
        this.profileActionListener = profileActionListener;
    }

    public interface ProfileActionListener {
        void openLoginFragment();

        void openAddMeasurementFragment();

        void openMeasurementGraphFragment();
    }

    private ProfileViewModel pViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_profile, container, false);
        pViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        binding.setProfileModel(pViewModel);
        binding.btnAddNewMeasurements.setOnClickListener(__ -> profileActionListener.openAddMeasurementFragment());
        binding.btnLogoff.setOnClickListener(__ -> {
            pViewModel.logoffAction();
            profileActionListener.openLoginFragment();
        });
        binding.btnShowMeasurementStatistics.setOnClickListener(v -> profileActionListener.openMeasurementGraphFragment());
        return binding.getRoot();
    }
}