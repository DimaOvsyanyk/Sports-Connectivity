package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;

import java.util.List;

@Dao
public interface DailyMenuDao {

    @Query("SELECT * FROM daily_menu WHERE user_id = :userId")
    LiveData<List<DailyMenu>> getDailyMenuByUserId (long userId);

    @Query("SELECT * FROM daily_menu WHERE id = :id")
    DailyMenu getMenuById(long id);

    @Query("SELECT * FROM daily_menu WHERE id = (SELECT MAX(ID) FROM daily_menu WHERE user_id = :userId)")
    DailyMenu getLastMenu(long userId);

    @Insert
    long insert(DailyMenu dailyMenu);

    @Update
    void update(DailyMenu dailyMenu);

    @Delete
    void delete(DailyMenu dailyMenu);
}
