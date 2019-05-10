package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private UserMeasurementsDao userMeasurementsDao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        userMeasurementsDao = db.userMeasurementsDao();
    }

    public long insert(User user) {
        return userDao.insert(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public User getByEmail(String eMail) {
        return userDao.getByEmail(eMail);
    }

    public User getByStayIn(int stayIn) {
        return userDao.getByStayIn(stayIn);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
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
}
