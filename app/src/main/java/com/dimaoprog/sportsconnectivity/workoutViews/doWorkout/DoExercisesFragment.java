package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentDoExercisesBinding;

import static com.dimaoprog.sportsconnectivity.Constants.EXERCISE_TO_DO_POSITION;
import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;
import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_ID;

public class DoExercisesFragment extends Fragment {

    public static DoExercisesFragment newInstance(long workoutToDoId, int exerciseToDoPosition) {
        Bundle args = new Bundle();
        args.putLong(WORKOUT_ID, workoutToDoId);
        args.putInt(EXERCISE_TO_DO_POSITION, exerciseToDoPosition);
        DoExercisesFragment fragment = new DoExercisesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    DoExercisesViewModel deViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deViewModel = ViewModelProviders.of(this).get(DoExercisesViewModel.class);
        deViewModel.setExerciseToDo(getArguments().getLong(WORKOUT_ID), getArguments().getInt(EXERCISE_TO_DO_POSITION));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDoExercisesBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_do_exercises, container, false);
        binding.setDoExerciseModel(deViewModel);
        final DoExerciseListAdapter doExerciseListAdapter = new DoExerciseListAdapter(deViewModel.getTempExerciseDoneList());
        binding.rwSetsDone.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rwSetsDone.setAdapter(doExerciseListAdapter);

        binding.btnAddSet.setOnClickListener(__ -> {
            deViewModel.addExerciseToDoneList();
            doExerciseListAdapter.notifyDataSetChanged();
        });
        return binding.getRoot();
    }
}