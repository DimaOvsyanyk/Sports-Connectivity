package com.dimaoprog.sportsconnectivity.dbWorkouts;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Query("SELECT * FROM workouts WHERE user_id = :userId")
    List<Workout> getByUserId(long userId);

    @Query("SELECT * FROM workouts")
    List<Workout> getAllWorkouts();

    @Insert
    long insert(Workout workout);

    @Update
    void update(Workout workout);

    @Delete
    void delete(Workout workout);

}
