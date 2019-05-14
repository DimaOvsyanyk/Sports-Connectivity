package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE workout_id = :workoutId")
    LiveData<List<Exercise>> getByWorkoutId(long workoutId);

    @Query("SELECT * FROM exercises WHERE workout_id = :workoutId")
    List<Exercise> getByWorkoutIdStaticList(long workoutId);

    @Insert
    long insert(Exercise exercise);

    @Update
    void update(Exercise exercise);

    @Delete
    void delete(Exercise exercise);
}
