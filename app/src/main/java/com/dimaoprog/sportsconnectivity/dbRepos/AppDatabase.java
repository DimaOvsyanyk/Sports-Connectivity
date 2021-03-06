package com.dimaoprog.sportsconnectivity.dbRepos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.dimaoprog.sportsconnectivity.Converter;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

@Database(entities = {User.class, Workout.class, Exercise.class, DailyMenu.class,
        Meal.class, UserMeasurements.class, ExerciseDone.class, MealDBFavorite.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract WorkoutDao workoutDao();

    public abstract ExerciseDao exerciseDao();

    public abstract DailyMenuDao dailyMenuDao();

    public abstract MealDao mealDao();

    public abstract UserMeasurementsDao userMeasurementsDao();

    public abstract ExerciseDoneDao exerciseDoneDao();

    public abstract MealDBFavoriteDao mealDBFavoriteDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "application_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
