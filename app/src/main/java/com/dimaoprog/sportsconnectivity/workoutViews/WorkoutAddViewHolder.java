package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.ForWorkoutsActivity;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.ItemExerciseAddBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutAddViewHolder extends RecyclerView.ViewHolder {

    private ItemExerciseAddBinding binding;

    public WorkoutAddViewHolder(@NonNull ItemExerciseAddBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Exercise exercise, final WorkoutAddAdapter.AddNewExerciseListener addNewExerciseListener) {
        binding.setExercise(exercise);
        binding.btnAddExerciseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewExerciseListener.showAddExerciseDialog();
            }
        });

    }


}
