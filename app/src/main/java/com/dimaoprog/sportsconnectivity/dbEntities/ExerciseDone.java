package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.dimaoprog.sportsconnectivity.Converter;

import java.util.Date;

@Entity(tableName = "exercise_done", foreignKeys = @ForeignKey(entity = Workout.class,
        parentColumns = "id", childColumns = "workout_id"))
public class ExerciseDone {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "workout_id", index = true)
    private long workoutId;

    @ColumnInfo(name = "exercise_title", index = true)
    private String exerciseTitle;

    @ColumnInfo(name = "weight_in_kg")
    private int weightInKg;

    private int reps;

    @ColumnInfo(name = "date_of_workout", index = true)
    private Date dateOfWorkout;

    public ExerciseDone(long workoutId, String exerciseTitle, int weightInKg, int reps, Date dateOfWorkout) {
        this.workoutId = workoutId;
        this.exerciseTitle = exerciseTitle;
        this.weightInKg = weightInKg;
        this.reps = reps;
        this.dateOfWorkout = dateOfWorkout;
    }

    @Ignore
    public ExerciseDone(String exerciseTitle, int weightInKg, int reps) {
        this.exerciseTitle = exerciseTitle;
        this.weightInKg = weightInKg;
        this.reps = reps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Date getDateOfWorkout() {
        return dateOfWorkout;
    }

    public void setDateOfWorkout(Date dateOfWorkout) {
        this.dateOfWorkout = dateOfWorkout;
    }
}
