package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.support.v7.widget.RecyclerView;

import com.dimaoprog.sportsconnectivity.databinding.ItemDoneSetBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;

public class DoExerciseViewHolder extends RecyclerView.ViewHolder {

    private ItemDoneSetBinding binding;

    public DoExerciseViewHolder(ItemDoneSetBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ExerciseDone exerciseDone) {
        binding.setExerciseDone(exerciseDone);
    }
}