package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbWorkouts.WorkoutsRepository;

public class WorkoutAddViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRep;
    private MutableLiveData<CharSequence> workoutTitle;
    private MutableLiveData<CharSequence> workoutMuscleGroups;
    private MutableLiveData<CharSequence> workoutDate;



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

    public LiveData<CharSequence> getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(CharSequence inputData) {
        workoutTitle.setValue(inputData);
    }

    public LiveData<CharSequence> getWorkoutMuscleGroups() {
        return workoutMuscleGroups;
    }

    public void setWorkoutMuscleGroups(CharSequence inputData) {
        workoutMuscleGroups.setValue(inputData);
    }

    public LiveData<CharSequence> getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(CharSequence inputData) {
        workoutDate.setValue(inputData);
    }
}
