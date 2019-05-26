package com.dimaoprog.sportsconnectivity.workoutViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.databinding.FragmentDetailWorkoutBinding;
import com.dimaoprog.sportsconnectivity.workoutViews.doWorkout.DoWorkoutFragment;

import java.util.Objects;

import javax.inject.Inject;

import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_ID;

public class DetailWorkoutFragment extends Fragment {

    public static DetailWorkoutFragment newInstance(long workoutId) {
        Bundle args = new Bundle();
        args.putLong(WORKOUT_ID, workoutId);
        DetailWorkoutFragment fragment = new DetailWorkoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private DetailWorkoutViewModel dwViewModel;
    private FragmentDetailWorkoutBinding binding;

    @Inject
    FragmentNaviManager navigation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_workout, container, false);
        AppComponentBuild.getComponent().inject(this);

        dwViewModel = ViewModelProviders.of(this).get(DetailWorkoutViewModel.class);
        dwViewModel.setWorkoutId(Objects.requireNonNull(getArguments()).getLong(WORKOUT_ID, -1));
        binding.setDetailWorkoutModel(dwViewModel);
        binding.btnStartWorkout.setOnClickListener(__ -> navigation.showNewFragment(DoWorkoutFragment.newInstance(dwViewModel.getWorkoutId())));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvDetailExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        final DetailWorkoutAdapter detailWorkoutAdapter = new DetailWorkoutAdapter();
        binding.rvDetailExercises.setAdapter(detailWorkoutAdapter);
        dwViewModel.getAllExercises().observe(this, exercises -> detailWorkoutAdapter.submitList(exercises));
    }
}