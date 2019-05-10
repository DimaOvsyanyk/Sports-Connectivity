package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import java.util.List;

import static com.dimaoprog.sportsconnectivity.Constants.NOTSTAY;

@Entity(tableName = "users", indices = @Index(value = "e_mail", unique = true))
public class User {

    @Ignore
    private static User ACTIVEUSER;

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "second_name")
    private String secondName;

    @ColumnInfo(name = "e_mail")
    private String eMail;

    private String password;

    @ColumnInfo(name = "stay_in_system")
    private int stayInSystem;

    @ColumnInfo(name = "date_of_birth")
    private String dateOfBirth;

    private String gender;

    @Ignore
    private List<Workout> workouts;

    public User(String firstName, String secondName, String eMail, String password, String dateOfBirth, String gender) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.eMail = eMail;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.stayInSystem = NOTSTAY;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStayInSystem(int stayInSystem) {
        this.stayInSystem = stayInSystem;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public int getStayInSystem() {
        return stayInSystem;
    }

    public static User getACTIVEUSER() {
        return ACTIVEUSER;
    }

    public static void setACTIVEUSER(User ACTIVEUSER) {
        User.ACTIVEUSER = ACTIVEUSER;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + secondName + " " + eMail + " " + password + " " + stayInSystem;
    }
}
