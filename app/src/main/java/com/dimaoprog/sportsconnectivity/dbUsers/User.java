package com.dimaoprog.sportsconnectivity.dbUsers;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "users")
public class User {

    public static final int STAY = 1;
    public static final int NOTSTAY = 0;

    @PrimaryKey (autoGenerate = true)
    public long id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "second_name")
    public String secondName;

    @ColumnInfo (name = "e_mail", index = true)
    public String eMail;

    public String password;

    @ColumnInfo (name = "stay_in_system", index = true)
    public int stayInSystem;

}
