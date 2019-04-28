package com.dimaoprog.sportsconnectivity.workoutViews;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailWorkoutFragment extends Fragment {

    public static final String WORKOUT_ID = "workout id";

    public static DetailWorkoutFragment newInstance(long workoutId) {
        Bundle args = new Bundle();
        args.putLong(WORKOUT_ID, workoutId);
        DetailWorkoutFragment fragment = new DetailWorkoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Unbinder unbinder;
    @BindView(R.id.txt_title)
    TextView title;
    @BindView(R.id.txt_date)
    TextView date;
    @BindView(R.id.txt_muscles)
    TextView muscleGroups;
    @BindView(R.id.rv_detail_exercises)
    RecyclerView rvExercises;

    private DetailWorkoutViewModel dwViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_workout, container, false);
        unbinder = ButterKnife.bind(this, v);

        dwViewModel = ViewModelProviders.of(this).get(DetailWorkoutViewModel.class);
        dwViewModel.setWorkoutId(getArguments().getLong(WORKOUT_ID, -1));

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        final DetailWorkoutAdapter detailWorkoutAdapter = new DetailWorkoutAdapter();
        rvExercises.setAdapter(detailWorkoutAdapter);
        dwViewModel.getAllExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> exercises) {
                detailWorkoutAdapter.submitList(exercises);
            }
        });
        fillViews(dwViewModel.getCurrentWorkout());
    }

    private void fillViews(Workout currentWorkout) {
        title.setText(currentWorkout.getWorkoutTitle());
        muscleGroups.setText(currentWorkout.getMuscleGroups());
        date.setText(currentWorkout.getDateOfWorkout());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
