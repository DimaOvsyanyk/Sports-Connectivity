package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbWorkouts.WorkoutsRepository;

import java.util.List;

public class DetailWorkoutViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRep;
    private LiveData<List<Exercise>> allExercises;
    private long workoutId;

    public DetailWorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutsRep = new WorkoutsRepository(application);
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public LiveData<List<Exercise>> getAllExercises() {
        allExercises = workoutsRep.getAllExercises(workoutId);
        return allExercises;
    }

    public Workout getCurrentWorkout() {
        return workoutsRep.getWorkoutById(workoutId);
    }


}
