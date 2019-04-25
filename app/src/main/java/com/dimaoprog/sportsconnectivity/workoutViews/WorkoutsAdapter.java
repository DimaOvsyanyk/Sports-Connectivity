package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.manager.WorkoutsManager;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> {

    public interface IDetailWorkoutListener{
        void openDetailWorkoutFragment(int i);
    }

    private IDetailWorkoutListener detailListener;

    public WorkoutsAdapter(IDetailWorkoutListener detailListener) {
        this.detailListener = detailListener;
    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.workout_item, viewGroup, false);
        return new WorkoutsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder workoutsViewHolder, int i) {
        workoutsViewHolder.bind(WorkoutsManager.getAllWorkouts().get(i), i, detailListener);
    }

    @Override
    public int getItemCount() {
        return WorkoutsManager.getAllWorkouts().size();
    }
}
