package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;

import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;

import java.util.List;

public class ProfileRepository {

    private UserMeasurementsDao userMeasurementsDao;

    public ProfileRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userMeasurementsDao = database.userMeasurementsDao();
    }

    public UserMeasurements getLastUserMeasurementById(long userId) {
        return userMeasurementsDao.getLastUserMeasurementById(userId);
    }

    public List<UserMeasurements> getUserMeasurementsList(long userId) {
        return userMeasurementsDao.getUserMeasurementsList(userId);
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
}
