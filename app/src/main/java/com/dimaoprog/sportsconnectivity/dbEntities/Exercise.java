package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "exercises", foreignKeys = @ForeignKey(entity = Workout.class,
        parentColumns = "id", childColumns = "workout_id", onDelete = ForeignKey.CASCADE))
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "workout_id", index = true)
    private long workoutId;

    @ColumnInfo(name = "exercise_title")
    private String exerciseTitle;

    private int rounds;

    private int repetitions;

    public Exercise(long workoutId, String exerciseTitle, int rounds, int repetitions) {
        this.workoutId = workoutId;
        this.exerciseTitle = exerciseTitle;
        this.rounds = rounds;
        this.repetitions = repetitions;
    }

    @Ignore
    public Exercise(String exerciseTitle, int rounds, int repetitions) {
        this.exerciseTitle = exerciseTitle;
        this.rounds = rounds;
        this.repetitions = repetitions;
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

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "Exercise ID - " + id + ", parent ID - " + workoutId + ", exercise - " + exerciseTitle +
                ", rounds - " + rounds + ", repetitions - " + repetitions;
    }
}
