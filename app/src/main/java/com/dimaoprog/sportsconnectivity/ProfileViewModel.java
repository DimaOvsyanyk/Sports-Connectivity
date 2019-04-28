package com.dimaoprog.sportsconnectivity;

import android.arch.lifecycle.ViewModel;

import com.dimaoprog.sportsconnectivity.dbEntities.User;

public class ProfileViewModel extends ViewModel {

    protected String getNameSurname() {
        return User.getACTIVEUSER().getFirstName() + " " +
                User.getACTIVEUSER().getSecondName();
    }
}
