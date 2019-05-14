package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;
import com.dimaoprog.sportsconnectivity.dbRepos.WorkoutsRepository;

import java.util.ArrayList;
import java.util.List;

import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;

public class DoExercisesViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRepo;
    private Exercise exerciseToDo;
    private List<ExerciseDone> tempExerciseDoneList = new ArrayList<>();
    private ObservableInt tempWeight = new ObservableInt();
    private ObservableInt tempReps = new ObservableInt();
    private ObservableInt currentSet = new ObservableInt();


    public DoExercisesViewModel(@NonNull Application application) {
        super(application);
        workoutsRepo = new WorkoutsRepository(application);
        setCurrentSet();
    }

    public Exercise getExerciseToDo() {
        return exerciseToDo;
    }

    public void setExerciseToDo(long workoutId, int exercisePosition) {
        exerciseToDo = workoutsRepo.getAllExercisesStaticList(workoutId).get(exercisePosition);
    }

    public ObservableInt getTempWeight() {
        return tempWeight;
    }

    public void setTempWeight(int tempWeight) {
        this.tempWeight.set(tempWeight);
    }

    public ObservableInt getTempReps() {
        return tempReps;
    }

    public void setTempReps(int tempReps) {
        this.tempReps.set(tempReps);
    }

    public List<ExerciseDone> getTempExerciseDoneList() {
        return tempExerciseDoneList;
    }

    public void setTempExerciseDoneList(List<ExerciseDone> exerciseDoneList) {
        this.tempExerciseDoneList = exerciseDoneList;
    }

    public ObservableInt getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet() {
        this.currentSet.set(tempExerciseDoneList.size() + 1);
    }

    public void addExerciseToDoneList() {
        ExerciseDone exerciseDone = new ExerciseDone(exerciseToDo.getExerciseTitle(), tempWeight.get(), tempReps.get());
        tempExerciseDoneList.add(exerciseDone);
        tempWeight.set(0);
        tempReps.set(0);
        setCurrentSet();
    }
}
