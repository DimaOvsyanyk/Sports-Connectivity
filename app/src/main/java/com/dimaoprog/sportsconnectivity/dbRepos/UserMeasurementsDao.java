package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;

import java.util.Date;
import java.util.List;

@Dao
public interface UserMeasurementsDao {

    @Query("SELECT * FROM user_measurements WHERE user_id = :userId ORDER BY date_of_measurement")
    List<UserMeasurements> getUserMeasurementsList(long userId);

    @Query("SELECT date_of_measurement FROM user_measurements WHERE id = (SELECT MAX(ID) FROM user_measurements WHERE user_id = :userId)")
    LiveData<Date> getLastUserMeasurementByUserId(long userId);

    @Query("SELECT * FROM user_measurements WHERE id = (SELECT MAX(ID) FROM user_measurements WHERE user_id = :userId)")
    UserMeasurements getLastUserMeasurementByUserIdStatic(long userId);

    @Insert
    void insert(UserMeasurements userMeasurement);

    @Update
    void update(UserMeasurements userMeasurement);

    @Delete
    void delete(UserMeasurements userMeasurement);
}
