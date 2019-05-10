package com.dimaoprog.sportsconnectivity.profileViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableDouble;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;
import com.dimaoprog.sportsconnectivity.dbRepos.ProfileRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddMeasurementsViewModel extends AndroidViewModel {

    private ProfileRepository profileRepo;
    private int height = 184;
    private int weight = 78;
    private int waist = 85;
    private int neck = 37;
    private int hip = 93;
    private ObservableDouble bodyFat = new ObservableDouble();
    private ObservableDouble bmi = new ObservableDouble();


    public AddMeasurementsViewModel(@NonNull Application application) {
        super(application);
        profileRepo = new ProfileRepository(application);
    }

    public void insertNewMeasurement(UserMeasurements userMeasurements) {
        profileRepo.insert(userMeasurements);
    }

    public void calculateFatBMI() {
        setBodyFat(86.010*Math.log10(waist-neck) - 70.041*Math.log10(height) + 30.30);
        setBmi((double) (weight/((height * height)/10000)));
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getNeck() {
        return neck;
    }

    public void setNeck(int neck) {
        this.neck = neck;
    }

    public int getHip() {
        return hip;
    }

    public void setHip(int hip) {
        this.hip = hip;
    }

    public ObservableDouble getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(Double bodyFat) {
        this.bodyFat.set(rounding(bodyFat));
    }

    public ObservableDouble getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi.set(rounding(bmi));
    }

    private double rounding(double longNumber) {
        return new BigDecimal(longNumber).setScale(2, RoundingMode.UP).doubleValue();
    }
}
