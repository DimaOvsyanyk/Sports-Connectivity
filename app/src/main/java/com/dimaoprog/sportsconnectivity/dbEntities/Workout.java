package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

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

    @ColumnInfo(name = "date_of_workout")
    private String dateOfWorkout;

    @Ignore
    private List<Exercise> exercises;

    public Workout(long userId, String workoutTitle, String muscleGroups, String dateOfWorkout) {
        this.userId = userId;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
        this.dateOfWorkout = dateOfWorkout;
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

    public void setMuscleGroups(String musclegroups) {
        this.muscleGroups = musclegroups;
    }

    public String getDateOfWorkout() {
        return dateOfWorkout;
    }

    public void setDateOfWorkout(String dateOfWorkout) {
        this.dateOfWorkout = dateOfWorkout;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exercises.size(); i++) {
            sb.append(i + 1);
            sb.append(" ");
            sb.append(exercises.get(i).toString());
        }
        return "Workout ID - " + id + ", title - " + workoutTitle + sb.toString();
    }
}
