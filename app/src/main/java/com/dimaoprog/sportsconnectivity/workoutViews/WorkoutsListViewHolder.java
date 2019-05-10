package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dimaoprog.sportsconnectivity.databinding.ItemWorkoutBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

public class WorkoutsListViewHolder extends RecyclerView.ViewHolder {

    private ItemWorkoutBinding binding;

    public WorkoutsListViewHolder(@NonNull ItemWorkoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(final Workout workout, final WorkoutsListAdapter.IDetailWorkoutListener listener) {
        binding.setWorkout(workout);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openDetailWorkoutFragment(workout.getId());
            }
        });
    }
}
