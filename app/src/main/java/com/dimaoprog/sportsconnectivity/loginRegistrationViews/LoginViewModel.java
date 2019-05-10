package com.dimaoprog.sportsconnectivity.loginRegistrationViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;

import static com.dimaoprog.sportsconnectivity.Constants.STAY;

public class LoginViewModel extends AndroidViewModel {

    private LoginFragment.OnPressedButtonListener onPressedButtonListener;
    private UserRepository userRepo;
    private String email;
    private String pass;
    private boolean stayIn;
    private boolean emailOk;
    private boolean passOk;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
        email = "";
        pass = "";
        stayIn = false;
        emailOk = true;
        passOk = true;
    }

    public boolean checkUser() {
        User tempUser = userRepo.getByEmail(email);
        if (tempUser == null) {
            emailOk = false;
            return false;
        } else if (!tempUser.getPassword().equals(pass)) {
            passOk = false;
            return false;
        } else {
            if (stayIn) {
                tempUser.setStayInSystem(STAY);
                userRepo.update(tempUser);
            }
            emailOk = true;
            passOk = true;
            User.setACTIVEUSER(tempUser);
            return true;
        }
    }

    public void setOnPressedButtonListener(LoginFragment.OnPressedButtonListener onPressedButtonListener) {
        this.onPressedButtonListener = onPressedButtonListener;
    }

    public void register() {
        onPressedButtonListener.onRegisterClick();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isStayIn() {
        return stayIn;
    }

    public void setStayIn(boolean stayIn) {
        this.stayIn = stayIn;
    }

    public boolean isEmailOk() {
        return emailOk;
    }

    public void setEmailOk(boolean emailOk) {
        this.emailOk = emailOk;
    }

    public boolean isPassOk() {
        return passOk;
    }

    public void setPassOk(boolean passOk) {
        this.passOk = passOk;
    }
}
