package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;

import java.util.List;

@Dao
public interface MealDBFavoriteDao {

    @Query("SELECT * FROM meal_db_favorite")
    LiveData<List<MealDBFavorite>> getAllMealsDB();

    @Query("SELECT * FROM meal_db_favorite WHERE idMeal = :mealDBid")
    MealDBFavorite getMealDBbyId (long mealDBid);

    @Insert
    void insert(MealDBFavorite mealDBFavorite);

    @Update
    void update(MealDBFavorite mealDBFavorite);

    @Delete
    void delete(MealDBFavorite mealDBFavorite);
}
