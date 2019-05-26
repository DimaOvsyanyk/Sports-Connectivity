package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbRepos.WorkoutsRepository;

import java.util.List;

public class DetailWorkoutViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRep;
    private LiveData<List<Exercise>> allExercises;
    private Workout currentWorkout;
    private long workoutId;

    public DetailWorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutsRep = new WorkoutsRepository(application);
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
        setCurrentWorkout(workoutsRep.getWorkoutById(workoutId));
        setAllExercises(workoutsRep.getAllExercises(workoutId));
    }

    public long getWorkoutId() {
        return workoutId;
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    private void setAllExercises(LiveData<List<Exercise>> allExercises) {
        this.allExercises = allExercises;
    }

    public Workout getCurrentWorkout() {
        return currentWorkout;
    }

    private void setCurrentWorkout(Workout currentWorkout) {
        this.currentWorkout = currentWorkout;
    }
}
