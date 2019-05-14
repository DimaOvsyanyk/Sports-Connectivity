package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbRepos.WorkoutsRepository;

import java.util.ArrayList;
import java.util.List;

public class DoWorkoutViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRepo;
    private Workout workoutToDo;
    private List<Exercise> exercisesToDo = new ArrayList<>();

    public DoWorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutsRepo = new WorkoutsRepository(application);
    }

    public Workout getWorkoutToDo() {
        return workoutToDo;
    }

    public void setWorkoutToDo(long workoutId) {
        workoutToDo = workoutsRepo.getWorkoutById(workoutId);
        setExercisesToDo(workoutsRepo.getAllExercisesStaticList(workoutId));
    }

    public List<Exercise> getExercisesToDo() {
        return exercisesToDo;
    }

    public void setExercisesToDo(List<Exercise> exercisesToDo) {
        this.exercisesToDo = exercisesToDo;
    }
}
