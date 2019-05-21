package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentDoWorkoutBinding;

import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_ID;

public class DoWorkoutFragment extends Fragment {

    public static DoWorkoutFragment newInstance(long workoutId) {

        Bundle args = new Bundle();
        args.putLong(WORKOUT_ID, workoutId);
        DoWorkoutFragment fragment = new DoWorkoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private DoWorkoutViewModel dwViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dwViewModel = ViewModelProviders.of(this).get(DoWorkoutViewModel.class);
        dwViewModel.setWorkoutToDo(getArguments().getLong(WORKOUT_ID));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDoWorkoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_do_workout, container, false);
        PagerAdapter pagerAdapter = new DoExercisePagerAdapter(getActivity().getSupportFragmentManager(), dwViewModel.getExercisesToDo());
        binding.workoutPager.setAdapter(pagerAdapter);
        binding.workoutPager.setOffscreenPageLimit(dwViewModel.getExercisesToDo().size() - 1);
        return binding.getRoot();
    }
}