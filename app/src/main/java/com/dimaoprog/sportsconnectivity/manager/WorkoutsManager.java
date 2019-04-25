package com.dimaoprog.sportsconnectivity.manager;

import android.content.Context;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbWorkouts.AppDatabase;
import com.dimaoprog.sportsconnectivity.dbWorkouts.ExerciseDao;
import com.dimaoprog.sportsconnectivity.dbWorkouts.WorkoutDao;
import java.util.ArrayList;
import java.util.List;

public class WorkoutsManager {

    public static final String WORKOUT_ID = "workout id";
    private static List<Workout> allWorkouts = new ArrayList<>();
    private static List<Exercise> allExercisesInWorkout = new ArrayList<>();

    public static List<Workout> getAllWorkouts() {
        return allWorkouts;
    }

    public static List<Exercise> getAllExercisesInWorkout() {
        return allExercisesInWorkout;
    }

    public static void setAllWorkouts(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        WorkoutDao workoutDao = db.workoutDao();
        allWorkouts = workoutDao.getByUserId(User.getACTIVEUSER().getId());
    }

    public static void setAllExercisesInWorkout(Context context, long workoutId) {
        AppDatabase db = AppDatabase.getInstance(context);
        ExerciseDao exerciseDao = db.exerciseDao();
        allExercisesInWorkout = exerciseDao.getByWorkoutId(workoutId);
    }
}