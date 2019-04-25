package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_exercise_title)
    TextView exerciseTitle;
    @BindView(R.id.txt_rounds)
    TextView txtRounds;
    @BindView(R.id.txt_reps)
    TextView txtReps;
    @BindView(R.id.btn_add_exercise_item)
    Button btnAddNewExercise;

    public ExerciseViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(Exercise exercise) {
        btnAddNewExercise.setVisibility(View.INVISIBLE);
        exerciseTitle.setText(exercise.getExerciseTitle());
        txtRounds.setText(String.valueOf(exercise.getRounds()));
        txtReps.setText(String.valueOf(exercise.getRepetitions()));
    }
}