package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.databinding.FragmentDoWorkoutBinding;

import java.util.Objects;

import javax.inject.Inject;

import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_ID;

public class DoWorkoutFragment extends Fragment implements DoExercisePagerAdapter.CheckCompleteExerciseDoneListener {

    public static DoWorkoutFragment newInstance(long workoutId) {
        Bundle args = new Bundle();
        args.putLong(WORKOUT_ID, workoutId);
        DoWorkoutFragment fragment = new DoWorkoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    FragmentNaviManager navigation;

    private DoWorkoutViewModel dwViewModel;
    private DoExercisePagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dwViewModel = ViewModelProviders.of(this).get(DoWorkoutViewModel.class);
        dwViewModel.setWorkoutToDo(Objects.requireNonNull(getArguments()).getLong(WORKOUT_ID));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDoWorkoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_do_workout, container, false);
        AppComponentBuild.getComponent().inject(this);
        pagerAdapter = new DoExercisePagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), dwViewModel.getExercisesToDo(), this);
        binding.workoutPager.setAdapter(pagerAdapter);
        binding.workoutPager.setOffscreenPageLimit(dwViewModel.getExercisesToDo().size() - 1);
        binding.setDoWorkoutModel(dwViewModel);
        binding.btnPostWorkout.setOnClickListener(v -> {
            dwViewModel.postDoneWorkout(pagerAdapter.getAllExercisesDoneLists());
            Toast.makeText(getContext(), "Workout done!", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        });

        return binding.getRoot();
    }

    @Override
    public void checkCompleteExerciseDone() {
        dwViewModel.setBtnPostVisibility(pagerAdapter.isAllExercisesDone());
    }
}