package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbRepos.WorkoutsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutAddViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRep;
    private List<Exercise> tempExercises = new ArrayList<>();
    private String workoutTitle;
    private ObservableField<Date> workoutDate = new ObservableField<>();
    private ObservableField<String> muscleGroups = new ObservableField<>();


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

    public boolean checkAllEntities() {
        return workoutDate.get() != null & muscleGroups.get().length() > 0 &
                workoutTitle.length() > 0 & tempExercises.size() > 0;
    }

    public void addWorkoutAndExercisesToDb() {
        Workout newWorkout = new Workout(User.getACTIVEUSER().getId(), workoutTitle,
                muscleGroups.get(), workoutDate.get());
        long newWorkoutId = insertWorkout(newWorkout);
        Exercise newExercise;
        for (int i = 0; i < tempExercises.size(); i++) {
            newExercise = tempExercises.get(i);
            newExercise.setWorkoutId(newWorkoutId);
            insertExercise(newExercise);
        }
    }

    public void setTempExercises(List<Exercise> tempExercises) {
        this.tempExercises = tempExercises;
    }

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public ObservableField<Date> getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Date workoutDate) {
        this.workoutDate.set(workoutDate);
    }

    public ObservableField<String> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(String muscleGroups) {
        this.muscleGroups.set(muscleGroups);
    }


}