package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.dimaoprog.sportsconnectivity.databinding.ItemExerciseAddBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

public class WorkoutAddViewHolder extends RecyclerView.ViewHolder {

    private ItemExerciseAddBinding binding;

    public WorkoutAddViewHolder(@NonNull ItemExerciseAddBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Exercise exercise, final WorkoutAddAdapter.AddNewExerciseListener addNewExerciseListener) {
        binding.setExercise(exercise);
        binding.btnAddExerciseItem.setOnClickListener(__ -> addNewExerciseListener.showAddExerciseDialog());
    }
}