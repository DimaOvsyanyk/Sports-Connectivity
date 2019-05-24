package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbRepos.StatisticRepository;
import com.dimaoprog.sportsconnectivity.dbRepos.WorkoutsRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;
import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_DONE;

public class DoWorkoutViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRepo;
    private StatisticRepository statisticRepo;
    private Workout workoutToDo;
    private List<Exercise> exercisesToDo = new ArrayList<>();
    private ObservableBoolean btnPostVisibility = new ObservableBoolean();

    public DoWorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutsRepo = new WorkoutsRepository(application);
        statisticRepo = new StatisticRepository(application);
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

    public ObservableBoolean getBtnPostVisibility() {
        return btnPostVisibility;
    }

    public void setBtnPostVisibility(boolean btnPostVisibility) {
        this.btnPostVisibility.set(btnPostVisibility);
    }

    public void postDoneWorkout(List<List<ExerciseDone>> allExercisesDoneLists) {
        Date today = new Date();
        today.setTime(Calendar.getInstance().getTimeInMillis());
        workoutToDo.setDateOfWorkout(today);
        workoutToDo.setWorkoutDoneFlag(WORKOUT_DONE);
        workoutsRepo.update(workoutToDo);
        ExerciseDone tempExerciseDone;
        for (int i = 0; i < allExercisesDoneLists.size(); i++) {
            for (int e = 0; e < allExercisesDoneLists.get(i).size(); e++) {
                tempExerciseDone = allExercisesDoneLists.get(i).get(e);
                tempExerciseDone.setDateOfWorkout(today);
                statisticRepo.insert(tempExerciseDone);
            }
        }
    }
}
