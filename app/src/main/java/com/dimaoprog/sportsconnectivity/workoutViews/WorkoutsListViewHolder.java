package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WorkoutsListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_muscle_groups)
    TextView txtMuscleGroups;

    public WorkoutsListViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(final Workout workout, final WorkoutsListAdapter.IDetailWorkoutListener listener) {
        txtTitle.setText(workout.getWorkoutTitle());
        txtMuscleGroups.setText(workout.getMuscleGroups());
        txtDate.setText(workout.getDateOfWorkout());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openDetailWorkoutFragment(workout.getId());
            }
        });
    }
}
