package com.dimaoprog.sportsconnectivity.profileViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;
import com.dimaoprog.sportsconnectivity.dbRepos.StatisticRepository;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;
import com.dimaoprog.sportsconnectivity.notification.NotificationHelper;

import static com.dimaoprog.sportsconnectivity.Constants.NOTSTAY;
import static com.dimaoprog.sportsconnectivity.Constants.STAY;

public class ProfileViewModel extends AndroidViewModel {

    private UserRepository userRepo;
    private StatisticRepository statisticRepo;
    private UserMeasurements lastMeasurement;
    private ObservableBoolean enableWorkoutReminder = new ObservableBoolean();
    protected Context appContext;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
        statisticRepo = new StatisticRepository(application);
        lastMeasurement = statisticRepo.getLastUserMeasurementById();
        appContext = application.getApplicationContext();
        enableWorkoutReminder.set(NotificationHelper.isAlarmManagerWorkoutOn());
    }

    public void update(User user) {
        userRepo.update(user);
    }

    public void logoffAction() {
        if (User.getACTIVEUSER().getStayInSystem() == STAY) {
            User.getACTIVEUSER().setStayInSystem(NOTSTAY);
            update(User.getACTIVEUSER());
        }
    }

    public String getNameSurname() {
        return User.getACTIVEUSER().getFirstName() + " " +
                User.getACTIVEUSER().getSecondName();
    }

    public ObservableBoolean getEnableWorkoutReminder() {
        return enableWorkoutReminder;
    }

    public void setEnableWorkoutReminder(boolean turnOn) {
        this.enableWorkoutReminder.set(turnOn);
    }

    public void turnOnWorkoutReminder(boolean turnOn) {
        if (turnOn) {
            NotificationHelper.workoutNotification(appContext);
            NotificationHelper.enableBootWorkoutReceiver(appContext);
        } else {
            NotificationHelper.cancelAlarmWorkout();
            NotificationHelper.disableBootWorkoutReceiver(appContext);
        }
    }

    public UserMeasurements getLastMeasurement() {
        return lastMeasurement;
    }

    public void setLastMeasurement(UserMeasurements lastMeasurement) {
        this.lastMeasurement = lastMeasurement;
    }
}
