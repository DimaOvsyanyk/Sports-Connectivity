package com.dimaoprog.sportsconnectivity.loginRegistrationViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;

import static com.dimaoprog.sportsconnectivity.Constants.FEMALE;
import static com.dimaoprog.sportsconnectivity.Constants.MALE;

public class RegistrationViewModel extends AndroidViewModel {

    private UserRepository userRepo;
    private String firstName = "";
    private String secondName = "";
    private String birthDay = "";
    private String email = "";
    private String password = "";
    private boolean male = false;
    private boolean female = false;

    private boolean firstNameOK;
    private boolean secondNameOK;
    private boolean genderOk;
    private boolean birthDayOk;
    private boolean emailOK;
    private boolean passwordOK;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
    }

    public long insert(User user) {
        return userRepo.insert(user);
    }

    public User getUserByEmail(String eMail) {
        return userRepo.getByEmail(eMail);
    }

    public boolean addUserToBD() {
        if (getUserByEmail(email) != null) {
            return false;
        }
        insert(new User(firstName, secondName, email, password, birthDay, checkedGender()));
        return true;
    }

    private String checkedGender() {
        String gender = "";
        if (male) {
            gender = MALE;
        }
        if (female) {
            gender = FEMALE;
        }
        return gender;
    }

    public boolean isInPutOK() {
        firstNameOK = firstName.length() > 0;
        secondNameOK = secondName.length() > 0;
        birthDayOk = birthDay.length() > 8;
        passwordOK = password.length() > 4;
        emailOK = checkEMail();
        setGenderOk();
        return (firstNameOK & secondNameOK & genderOk & birthDayOk & emailOK & passwordOK);
    }

    private boolean checkEMail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return (email.matches(emailPattern));
    }

    public boolean isFirstNameOK() {
        return firstNameOK;
    }

    public void setFirstNameOK(boolean firstNameOK) {
        this.firstNameOK = firstNameOK;
    }

    public boolean isSecondNameOK() {
        return secondNameOK;
    }

    public void setSecondNameOK(boolean secondNameOK) {
        this.secondNameOK = secondNameOK;
    }

    public boolean isGenderOk() {
        return genderOk;
    }

    public void setGenderOk() {
        genderOk = male | female;
    }

    public boolean isBirthDayOk() {
        return birthDayOk;
    }

    public void setBirthDayOk(boolean birthDayOk) {
        this.birthDayOk = birthDayOk;
    }

    public boolean isEmailOK() {
        return emailOK;
    }

    public void setEmailOK(boolean eMailOK) {
        this.emailOK = eMailOK;
    }

    public boolean isPasswordOK() {
        return passwordOK;
    }

    public void setPasswordOK(boolean passwordOK) {
        this.passwordOK = passwordOK;
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
        this.secondName = secondName.trim();
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
