package com.dimaoprog.sportsconnectivity.profileViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.applandeo.materialcalendarview.EventDay;
import com.dimaoprog.sportsconnectivity.Converter;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbRepos.StatisticRepository;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;
import com.dimaoprog.sportsconnectivity.notification.NotificationHelper;

import java.util.Date;
import java.util.List;

import static com.dimaoprog.sportsconnectivity.Constants.FEMALE;
import static com.dimaoprog.sportsconnectivity.Constants.MALE;
import static com.dimaoprog.sportsconnectivity.Constants.NOTSTAY;
import static com.dimaoprog.sportsconnectivity.Constants.STAY;

public class ProfileViewModel extends AndroidViewModel {

    private UserRepository userRepo;
    private StatisticRepository statisticRepo;
    private Date lastMeasurementDate;
    private LiveData<Date> lastMeasurement;
    private ObservableBoolean enableWorkoutReminder = new ObservableBoolean();
    private Context appContext;
    private List<EventDay> eventDayList;
    private List<Workout> allDoneWorkouts;
    private ObservableField<String> nameSurname = new ObservableField<>();

    private String firstName;
    private String secondName;
    private String birthDay;
    private String email;
    private String password;
    private boolean male;
    private boolean female;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
        statisticRepo = new StatisticRepository(application);
        lastMeasurement = statisticRepo.getLastUserMeasurement();
        appContext = application.getApplicationContext();
        enableWorkoutReminder.set(NotificationHelper.isAlarmManagerWorkoutOn());
        allDoneWorkouts = statisticRepo.getAllDoneWorkouts();
        eventDayList = Converter.workoutToEventList(allDoneWorkouts);
        setNameSurname();

    }

    public Date getLastMeasurementDate() {
        return lastMeasurementDate;
    }

    public void setLastMeasurementDate(Date lastMeasurementDate) {
        this.lastMeasurementDate = lastMeasurementDate;
    }

    public List<Workout> getAllDoneWorkouts() {
        return allDoneWorkouts;
    }

    public void setAllDoneWorkouts(List<Workout> allDoneWorkouts) {
        this.allDoneWorkouts = allDoneWorkouts;
    }

    public void setActiveUserDetails() {
        firstName = User.getACTIVEUSER().getFirstName();
        secondName = User.getACTIVEUSER().getSecondName();
        birthDay = User.getACTIVEUSER().getDateOfBirth();
        email = User.getACTIVEUSER().getEMail();
        password = User.getACTIVEUSER().getPassword();
        if (User.getACTIVEUSER().getGender().equals(MALE)) {
            male = true;
            female = false;
        } else {
            male = false;
            female = true;
        }
    }

    public List<EventDay> getEventDayList() {
        return eventDayList;
    }

    private void update(User user) {
        userRepo.update(user);
    }

    public void logoffAction() {
        if (User.getACTIVEUSER().getStayInSystem() == STAY) {
            User.getACTIVEUSER().setStayInSystem(NOTSTAY);
            update(User.getACTIVEUSER());
        }
        User.setACTIVEUSER(null);
    }

    public ObservableField<String> getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname() {
        nameSurname.set(getNameSurnameFromUser());
    }

    public String getNameSurnameFromUser() {
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

    public LiveData<Date> getLastMeasurement() {
        return lastMeasurement;
    }

    public void updateActiveUser() {
        User newActiveUser = User.getACTIVEUSER();
        newActiveUser.setFirstName(firstName);
        newActiveUser.setSecondName(secondName);
        newActiveUser.setDateOfBirth(birthDay);
        newActiveUser.setEMail(email);
        newActiveUser.setPassword(password);
        newActiveUser.setGender(defineGender());
        update(newActiveUser);
        setNameSurname();
    }

    private String defineGender() {
        String gender = "";
        if (male) {
            gender = MALE;
        }
        if (female) {
            gender = FEMALE;
        }
        return gender;
    }

    public boolean isUserDetailsOk() {
        return firstName.length() > 0 &
                secondName.length() > 0 &
                birthDay.length() > 8 &
                password.length() > 4 &
                checkEMail();
    }

    private boolean checkEMail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return (email.matches(emailPattern));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName.intern();
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }
}
