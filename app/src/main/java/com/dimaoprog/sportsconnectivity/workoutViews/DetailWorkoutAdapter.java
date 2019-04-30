package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

public class DetailWorkoutAdapter extends ListAdapter<Exercise, DetailWorkoutViewHolder> {

    protected DetailWorkoutAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Exercise> DIFF_CALLBACK = new DiffUtil.ItemCallback<Exercise>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise exercise, @NonNull Exercise t1) {
            return exercise.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise exercise, @NonNull Exercise t1) {
            return exercise.getWorkoutId() == t1.getWorkoutId() &&
                    exercise.getExerciseTitle().equals(t1.getExerciseTitle()) &&
                    exercise.getRounds() == t1.getRounds() &&
                    exercise.getRepetitions() == t1.getRepetitions();
        }
    };

    @NonNull
    @Override
    public DetailWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise_add, viewGroup, false);
        return new DetailWorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailWorkoutViewHolder detailWorkoutViewHolder, int i) {
        detailWorkoutViewHolder.bind(getItem(i));
    }

}