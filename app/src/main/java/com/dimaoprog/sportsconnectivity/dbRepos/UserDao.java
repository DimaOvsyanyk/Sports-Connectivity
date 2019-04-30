package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dimaoprog.sportsconnectivity.dbEntities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE e_mail = :eMail")
    User getByEmail(String eMail);

    @Query("SELECT * FROM users WHERE stay_in_system = :stayIn")
    User getByStayIn(int stayIn);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Insert
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

}
