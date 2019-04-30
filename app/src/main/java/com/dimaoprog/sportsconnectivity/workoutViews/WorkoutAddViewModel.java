package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbRepos.WorkoutsRepository;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAddViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRep;
    private List<Exercise> tempExercises = new ArrayList<>();


    public WorkoutAddViewModel(@NonNull Application application) {
        super(application);
        workoutsRep = new WorkoutsRepository(application);
    }

    public long insertWorkout(Workout workout) {
        return workoutsRep.insert(workout);
    }

    public void insertExercise(Exercise exercise) {
        workoutsRep.insert(exercise);
    }

    public void addNewExerciseToWorkout(Exercise exercise) {
        tempExercises.add(exercise);
    }

    public List<Exercise> getTempExercises() {
        return tempExercises;
    }

    public void deleteExerciseFromWorkout(Exercise exercise) {
        tempExercises.remove(exercise);
    }

}
