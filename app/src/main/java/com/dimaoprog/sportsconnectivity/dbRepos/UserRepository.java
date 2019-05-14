package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;

import java.util.List;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
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

}
