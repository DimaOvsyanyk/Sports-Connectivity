package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dimaoprog.sportsconnectivity.databinding.ItemExerciseAddBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

public class DetailWorkoutViewHolder extends RecyclerView.ViewHolder {

    private ItemExerciseAddBinding binding;

    public DetailWorkoutViewHolder(@NonNull ItemExerciseAddBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Exercise exercise) {
        binding.btnAddExerciseItem.setVisibility(View.INVISIBLE);
        binding.setExercise(exercise);
    }
}