package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;

public class AddExerciseAdapter extends RecyclerView.Adapter<AddExerciseViewHolder> {

    private AddNewExerciseListener addNewExerciseListener;

    public interface AddNewExerciseListener{
        void showAddExerciseDialog();
    }

    public AddExerciseAdapter(AddNewExerciseListener addNewExerciseListener) {
        this.addNewExerciseListener = addNewExerciseListener;
    }

    @NonNull
    @Override
    public AddExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exercise_add_item, viewGroup, false);
        return new AddExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddExerciseViewHolder addExerciseViewHolder, int i) {
        addExerciseViewHolder.bind(WorkoutAddFragment.tempExercises.get(i), addNewExerciseListener);
    }

    @Override
    public int getItemCount() {
        return WorkoutAddFragment.tempExercises.size();
    }
}
