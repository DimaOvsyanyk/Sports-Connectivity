package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.ForWorkoutsActivity;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAddAdapter extends RecyclerView.Adapter<WorkoutAddViewHolder> {

    private AddNewExerciseListener addNewExerciseListener;
    private List<Exercise> exercises;

    public interface AddNewExerciseListener{
        void showAddExerciseDialog();
    }

    public WorkoutAddAdapter(AddNewExerciseListener addNewExerciseListener, List<Exercise> exercises) {
        Log.d(ForWorkoutsActivity.LOG_MAIN, "adapter creating start");
        this.addNewExerciseListener = addNewExerciseListener;
        this.exercises = exercises;
        Log.d(ForWorkoutsActivity.LOG_MAIN, "adapter created");
    }

    @NonNull
    @Override
    public WorkoutAddViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise_add, viewGroup, false);
        return new WorkoutAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAddViewHolder workoutAddViewHolder, int i) {
        workoutAddViewHolder.bind(exercises.get(i), addNewExerciseListener);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public Exercise getExerciseAtPos(int position) {
        return exercises.get(position);
    }
}
