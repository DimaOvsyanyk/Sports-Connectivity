package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.ItemDoneSetBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;

import java.util.ArrayList;
import java.util.List;

public class DoExerciseListAdapter extends RecyclerView.Adapter<DoExerciseViewHolder> {

    private List<ExerciseDone> exerciseDoneList;

    public DoExerciseListAdapter(List<ExerciseDone> exerciseDoneList) {
        this.exerciseDoneList = exerciseDoneList;
    }

    @NonNull
    @Override
    public DoExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDoneSetBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_done_set, viewGroup, false);
        return new DoExerciseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DoExerciseViewHolder doExerciseViewHolder, int i) {
        doExerciseViewHolder.bind(exerciseDoneList.get(i));
    }

    @Override
    public int getItemCount() {
        return exerciseDoneList.size();
    }
}
