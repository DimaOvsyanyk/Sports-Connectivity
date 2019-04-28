package com.dimaoprog.sportsconnectivity.workoutViews;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

public class WorkoutsListAdapter extends ListAdapter<Workout, WorkoutsListViewHolder> {

    public interface IDetailWorkoutListener{
        void openDetailWorkoutFragment(long workoutId);
    }

    private IDetailWorkoutListener detailListener;

    public WorkoutsListAdapter(IDetailWorkoutListener detailListener) {
        super(DIFF_CALLBACK);
        this.detailListener = detailListener;
    }

    private static final DiffUtil.ItemCallback<Workout> DIFF_CALLBACK = new DiffUtil.ItemCallback<Workout>() {
        @Override
        public boolean areItemsTheSame(@NonNull Workout workout, @NonNull Workout t1) {
            return workout.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Workout workout, @NonNull Workout t1) {
            return workout.getWorkoutTitle().equals(t1.getWorkoutTitle()) &&
                    workout.getMuscleGroups().equals(t1.getMuscleGroups()) &&
                    workout.getDateOfWorkout().equals(t1.getDateOfWorkout());
        }
    };

    @NonNull
    @Override
    public WorkoutsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.workout_item, viewGroup, false);
        return new WorkoutsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsListViewHolder workoutsListViewHolder, int i) {
        workoutsListViewHolder.bind(getItem(i), detailListener);
    }

    public Workout getWorkoutAtPos(int position) {
        return getItem(position);
    }

}
