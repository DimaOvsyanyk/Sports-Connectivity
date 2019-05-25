package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import java.util.Date;
import java.util.List;

import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_DONE;

public class StatisticRepository {

    private UserMeasurementsDao userMeasurementsDao;
    private ExerciseDoneDao exerciseDoneDao;
    private WorkoutDao workoutDao;
    private LiveData<Date> lastUserMeasurement;
    private List<Workout> allDoneWorkouts;

    public StatisticRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userMeasurementsDao = db.userMeasurementsDao();
        exerciseDoneDao = db.exerciseDoneDao();
        workoutDao = db.workoutDao();
        lastUserMeasurement = userMeasurementsDao.getLastUserMeasurementByUserId(User.getACTIVEUSER().getId());
        allDoneWorkouts = workoutDao.getByUserIdStaticList(User.getACTIVEUSER().getId(), WORKOUT_DONE);
    }

    public List<Workout> getAllDoneWorkouts() {
        return allDoneWorkouts;
    }

    public void insert(UserMeasurements userMeasurements) {
        userMeasurementsDao.insert(userMeasurements);
    }

    public void update(UserMeasurements userMeasurements) {
        userMeasurementsDao.update(userMeasurements);
    }

    public void delete(UserMeasurements userMeasurements) {
        userMeasurementsDao.delete(userMeasurements);
    }

    public List<UserMeasurements> getUserMeasurementsList() {
        return userMeasurementsDao.getUserMeasurementsList(User.getACTIVEUSER().getId());
    }

    public UserMeasurements getLastUserMeasurementStatic() {
        return userMeasurementsDao.getLastUserMeasurementByUserIdStatic(User.getACTIVEUSER().getId());
    }

    public LiveData<Date> getLastUserMeasurement() {
        return lastUserMeasurement;
    }

    public void insert(ExerciseDone exerciseDone) {
        exerciseDoneDao.insert(exerciseDone);
    }

    public void insert(List<ExerciseDone> exerciseDoneList) {
        exerciseDoneDao.insert(exerciseDoneList);
    }

    public void update(ExerciseDone exerciseDone) {
        exerciseDoneDao.update(exerciseDone);
    }

    public void delete(ExerciseDone exerciseDone) {
        exerciseDoneDao.delete(exerciseDone);
    }

    public List<String> getAllExercisesList() {
        return exerciseDoneDao.getAllExercisesList();
    }

    public List<ExerciseDone> getExercisesDoneByTitle(String exerciseTitle) {
        return exerciseDoneDao.getExercisesDoneByTitle(exerciseTitle);
    }
}
