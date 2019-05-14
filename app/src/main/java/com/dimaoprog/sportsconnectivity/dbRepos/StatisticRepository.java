package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;

import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;

import java.util.List;

public class StatisticRepository {

    private UserMeasurementsDao userMeasurementsDao;
    private ExerciseDoneDao exerciseDoneDao;

    public StatisticRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userMeasurementsDao = db.userMeasurementsDao();
        exerciseDoneDao = db.exerciseDoneDao();
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

    public UserMeasurements getLastUserMeasurementById() {
        return userMeasurementsDao.getLastUserMeasurementById(User.getACTIVEUSER().getId());
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
