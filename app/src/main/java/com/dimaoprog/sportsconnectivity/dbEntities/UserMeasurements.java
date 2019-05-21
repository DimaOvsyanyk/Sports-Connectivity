package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user_measurements", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class UserMeasurements {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_id", index = true)
    private long userId;

    @ColumnInfo(name = "date_of_measurement", index = true)
    private Date dateOfMeasurement;

    @ColumnInfo(name = "height_in_cm")
    private int heightInCM;

    @ColumnInfo(name = "weight_in_kg")
    private int weightInKG;

    @ColumnInfo(name = "waist_girth_in_cm")
    private int waistGirthInCM;

    @ColumnInfo(name = "neck_girth_in_cm")
    private int neckGirthInCM;

    @ColumnInfo(name = "hip_girth_in_cm")
    private int hipGirthInCM;

    @ColumnInfo(name = "body_fat")
    private double bodyFat;

    private double bmi;

    public UserMeasurements(long userId, Date dateOfMeasurement, int heightInCM, int weightInKG, int waistGirthInCM,
                            int neckGirthInCM, int hipGirthInCM, double bodyFat, double bmi) {
        this.userId = userId;
        this.dateOfMeasurement = dateOfMeasurement;
        this.heightInCM = heightInCM;
        this.weightInKG = weightInKG;
        this.waistGirthInCM = waistGirthInCM;
        this.neckGirthInCM = neckGirthInCM;
        this.hipGirthInCM = hipGirthInCM;
        this.bodyFat = bodyFat;
        this.bmi = bmi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDateOfMeasurement() {
        return dateOfMeasurement;
    }

    public void setDateOfMeasurement(Date dateOfMeasurement) {
        this.dateOfMeasurement = dateOfMeasurement;
    }

    public int getHeightInCM() {
        return heightInCM;
    }

    public void setHeightInCM(int heightInCM) {
        this.heightInCM = heightInCM;
    }

    public int getWeightInKG() {
        return weightInKG;
    }

    public void setWeightInKG(int weightInKG) {
        this.weightInKG = weightInKG;
    }

    public int getWaistGirthInCM() {
        return waistGirthInCM;
    }

    public void setWaistGirthInCM(int waistGirthInCM) {
        this.waistGirthInCM = waistGirthInCM;
    }

    public int getNeckGirthInCM() {
        return neckGirthInCM;
    }

    public void setNeckGirthInCM(int neckGirthInCM) {
        this.neckGirthInCM = neckGirthInCM;
    }

    public int getHipGirthInCM() {
        return hipGirthInCM;
    }

    public void setHipGirthInCM(int hipGirthInCM) {
        this.hipGirthInCM = hipGirthInCM;
    }

    public double getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(double bodyFat) {
        this.bodyFat = bodyFat;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
