package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;

public class WorkoutAddAdapter extends RecyclerView.Adapter<WorkoutAddViewHolder> {

    private AddNewExerciseListener addNewExerciseListener;

    public interface AddNewExerciseListener{
        void showAddExerciseDialog();
    }

    public WorkoutAddAdapter(AddNewExerciseListener addNewExerciseListener) {
        this.addNewExerciseListener = addNewExerciseListener;
    }

    @NonNull
    @Override
    public WorkoutAddViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exercise_add_item, viewGroup, false);
        return new WorkoutAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAddViewHolder workoutAddViewHolder, int i) {
        workoutAddViewHolder.bind(WorkoutAddFragment.tempExercises.get(i), addNewExerciseListener);
    }

    @Override
    public int getItemCount() {
        return WorkoutAddFragment.tempExercises.size();
    }
}
