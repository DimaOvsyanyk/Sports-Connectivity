package com.dimaoprog.sportsconnectivity.workoutViews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.manager.WorkoutsManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailWorkoutFragment extends Fragment {

    public static DetailWorkoutFragment newInstance(int i) {
        Bundle args = new Bundle();
        args.putInt(WorkoutsManager.WORKOUT_ID, i);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_workout, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int i = getArguments().getInt(WorkoutsManager.WORKOUT_ID, -1);
        Workout someWorkout = WorkoutsManager.getAllWorkouts().get(i);
        WorkoutsManager.setAllExercisesInWorkout(getContext(), someWorkout.getId());
        rvExercises.setAdapter(new ExerciseAdapter());
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        fillViews(someWorkout);
    }

    private void fillViews(Workout someWorkout) {
        title.setText(someWorkout.getWorkoutTitle());
        muscleGroups.setText(someWorkout.getMuscleGroups());
        date.setText(someWorkout.getDateOfWorkout());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
