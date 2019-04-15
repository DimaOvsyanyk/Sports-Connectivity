package com.dimaoprog.sportsconnectivity.dbUsers;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {

    public static App instance;
    private UserDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, UserDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public UserDatabase getDatabase() {
        return database;
    }
}
