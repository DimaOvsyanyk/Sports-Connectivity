package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT * FROM meal WHERE daily_menu_id = :menuId")
    LiveData<List<Meal>> getMealsByDailyMenuId (long menuId);

    @Insert
    long insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);
}
