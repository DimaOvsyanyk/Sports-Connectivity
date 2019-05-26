package com.dimaoprog.sportsconnectivity.profileViews;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.databinding.DialogEditProfileBinding;
import com.dimaoprog.sportsconnectivity.databinding.FragmentProfileBinding;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.LoginFragment;

import javax.inject.Inject;

public class ProfileFragment extends Fragment {

    private ProfileViewModel pViewModel;

    @Inject
    FragmentNaviManager navigation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_profile, container, false);
        AppComponentBuild.getComponent().inject(this);
        pViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        pViewModel.getLastMeasurement().observe(this, date -> binding.setLastDate(date));
        binding.setProfileModel(pViewModel);

        binding.btnAddNewMeasurements.setOnClickListener(__ -> navigation.showNewFragment(new AddMeasurementsFragment()));

        binding.btnLogoff.setOnClickListener(__ -> {
            pViewModel.logoffAction();
            navigation.showNewFragment(new LoginFragment());
        });

        binding.btnShowMeasurementStatistics.setOnClickListener(v -> navigation.showNewFragment(new MeasurementGraphFragment()));

        binding.btnDetailWorkoutStatistic.setOnClickListener(v -> {
            binding.calendarView.setVisibility(rotate(v));
            binding.calendarView.setEvents(pViewModel.getEventDayList());
        });
        binding.btnEditProfile.setOnClickListener(__ -> showAddEditDialog());
        return binding.getRoot();
    }

    public void showAddEditDialog() {
        pViewModel.setActiveUserDetails();
        final Dialog dialogEditProfile = new Dialog(getContext());
        dialogEditProfile.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogEditProfileBinding bindingEditProfile = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_edit_profile, null, false);
        dialogEditProfile.setContentView(bindingEditProfile.getRoot());
        dialogEditProfile.setCanceledOnTouchOutside(true);
        bindingEditProfile.setProfileModel(pViewModel);

        bindingEditProfile.btnSave.setOnClickListener(__ -> {
            if (pViewModel.isUserDetailsOk()) {
                pViewModel.updateActiveUser();
                dialogEditProfile.dismiss();
                Toast.makeText(getContext(), "Profile details updated", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getContext(), "You have invalid entities", Toast.LENGTH_SHORT).show();
            }
        });
        dialogEditProfile.show();
    }

    private int rotate(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return View.VISIBLE;
        } else {
            view.animate().setDuration(200).rotation(0);
            return View.GONE;
        }
    }
}