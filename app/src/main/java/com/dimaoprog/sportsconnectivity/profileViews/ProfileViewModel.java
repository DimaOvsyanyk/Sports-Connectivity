package com.dimaoprog.sportsconnectivity.profileViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;
import com.dimaoprog.sportsconnectivity.dbRepos.ProfileRepository;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;
import java.util.List;

import static com.dimaoprog.sportsconnectivity.Constants.NOTSTAY;
import static com.dimaoprog.sportsconnectivity.Constants.STAY;

public class ProfileViewModel extends AndroidViewModel {

    private UserRepository userRepo;
    private ProfileRepository profileRepo;
    private List<User> allUsers;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
        profileRepo = new ProfileRepository(application);
        allUsers = userRepo.getAllUsers();
    }

    public UserMeasurements getLastMeasurement() {
        return profileRepo.getLastUserMeasurementById(User.getACTIVEUSER().getId());
    }

    public List<User> getAllUsers() {
        return allUsers;
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

    protected String getNameSurname() {
        return User.getACTIVEUSER().getFirstName() + " " +
                User.getACTIVEUSER().getSecondName();
    }
}
