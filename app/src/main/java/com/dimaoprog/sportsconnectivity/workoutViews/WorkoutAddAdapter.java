package com.dimaoprog.sportsconnectivity.workoutViews;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.ItemExerciseAddBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

import java.util.List;

public class WorkoutAddAdapter extends RecyclerView.Adapter<WorkoutAddViewHolder> {

    private AddNewExerciseListener addNewExerciseListener;
    private List<Exercise> exercises;

    public interface AddNewExerciseListener {
        void showAddExerciseDialog();
    }

    public WorkoutAddAdapter(AddNewExerciseListener addNewExerciseListener, List<Exercise> exercises) {
        this.addNewExerciseListener = addNewExerciseListener;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public WorkoutAddViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemExerciseAddBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_exercise_add, viewGroup, false);
        return new WorkoutAddViewHolder(binding);
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
