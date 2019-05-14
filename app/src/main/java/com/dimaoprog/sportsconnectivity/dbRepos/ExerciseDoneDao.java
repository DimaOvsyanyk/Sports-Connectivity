package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;

import java.util.List;

@Dao
public interface ExerciseDoneDao {

    @Query("SELECT * FROM exercise_done WHERE exercise_title = :exerciseTitle ORDER BY date_of_workout")
    List<ExerciseDone> getExercisesDoneByTitle(String exerciseTitle);

    @Query("SELECT DISTINCT exercise_title FROM exercise_done")
    List<String> getAllExercisesList();

    @Insert
    void insert(ExerciseDone exerciseDone);

    @Insert
    void insert(List<ExerciseDone> exerciseDoneList);

    @Update
    void update(ExerciseDone exerciseDone);

    @Delete
    void delete(ExerciseDone exerciseDone);


}
