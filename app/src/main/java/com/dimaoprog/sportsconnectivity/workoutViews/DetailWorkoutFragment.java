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

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentDetailWorkoutBinding;

import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_ID;

public class DetailWorkoutFragment extends Fragment {

    public static DetailWorkoutFragment newInstance(long workoutId, StartWorkoutListener startWorkoutListener) {
        Bundle args = new Bundle();
        args.putLong(WORKOUT_ID, workoutId);
        DetailWorkoutFragment fragment = new DetailWorkoutFragment();
        fragment.setArguments(args);
        fragment.setStartWorkoutListener(startWorkoutListener);
        return fragment;
    }

    StartWorkoutListener startWorkoutListener;

    public interface StartWorkoutListener {
        void openDoWorkoutFragment(long workoutId);
    }

    public void setStartWorkoutListener(StartWorkoutListener startWorkoutListener) {
        this.startWorkoutListener = startWorkoutListener;
    }

    private DetailWorkoutViewModel dwViewModel;
    private FragmentDetailWorkoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_workout, container, false);

        dwViewModel = ViewModelProviders.of(this).get(DetailWorkoutViewModel.class);
        dwViewModel.setWorkoutId(getArguments().getLong(WORKOUT_ID, -1));
        binding.setDetailWorkoutModel(dwViewModel);
        binding.btnStartWorkout.setOnClickListener(__ -> startWorkoutListener.openDoWorkoutFragment(dwViewModel.getWorkoutId()));
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