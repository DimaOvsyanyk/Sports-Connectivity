package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

import static com.dimaoprog.sportsconnectivity.Constants.WORKOUT_NOT_DONE;

@Entity(tableName = "workouts", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class Workout {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_id", index = true)
    private long userId;

    @ColumnInfo(name = "workout_title")
    private String workoutTitle;

    @ColumnInfo(name = "muscle_groups")
    private String muscleGroups;

    @ColumnInfo(name = "date_of_workout", index = true)
    private Date dateOfWorkout;

    @ColumnInfo(name = "workout_done_flag", index = true)
    private int workoutDoneFlag;

    @Ignore
    private List<Exercise> exercises;

    public Workout(long userId, String workoutTitle, String muscleGroups, Date dateOfWorkout) {
        this.userId = userId;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
        this.dateOfWorkout = dateOfWorkout;
        this.workoutDoneFlag = WORKOUT_NOT_DONE;
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

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public String getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(String muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public Date getDateOfWorkout() {
        return dateOfWorkout;
    }

    public void setDateOfWorkout(Date dateOfWorkout) {
        this.dateOfWorkout = dateOfWorkout;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getWorkoutDoneFlag() {
        return workoutDoneFlag;
    }

    public void setWorkoutDoneFlag(int workoutDoneFlag) {
        this.workoutDoneFlag = workoutDoneFlag;
    }

    @Override
    public String toString() {
        return "Workout ID - " + id + ", title - " + workoutTitle + " flag - " + workoutDoneFlag;
    }
}
