package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
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
    private ObservableBoolean btnAddSetVisibility = new ObservableBoolean();

    public DoExercisesViewModel(@NonNull Application application) {
        super(application);
        workoutsRepo = new WorkoutsRepository(application);
    }

    public Exercise getExerciseToDo() {
        return exerciseToDo;
    }

    public void setExerciseToDo(long workoutId, int exercisePosition) {
        exerciseToDo = workoutsRepo.getAllExercisesStaticList(workoutId).get(exercisePosition);
        setCurrentSet();
        setBtnAddSetVisibility();
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
        if (exerciseToDo.getRounds() == currentSet.get()) {
            currentSet.set(tempExerciseDoneList.size());
        } else {
            currentSet.set(tempExerciseDoneList.size() + 1);
        }
    }

    public void addExerciseToDoneList() {
        ExerciseDone exerciseDone = new ExerciseDone(exerciseToDo.getWorkoutId(), exerciseToDo.getExerciseTitle(), tempWeight.get(), tempReps.get());
        tempExerciseDoneList.add(exerciseDone);
        tempWeight.set(0);
        tempReps.set(0);
        setCurrentSet();
        setBtnAddSetVisibility();
    }

    public ObservableBoolean getBtnAddSetVisibility() {
        return btnAddSetVisibility;
    }

    public void setBtnAddSetVisibility() {
        btnAddSetVisibility.set(!(tempExerciseDoneList.size() == exerciseToDo.getRounds()));
    }
}
