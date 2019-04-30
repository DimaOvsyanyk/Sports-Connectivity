package com.dimaoprog.sportsconnectivity.dbRepos;

import android.content.Context;

import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONReader {
    private static final String TAG_WEEK_WORKOUTS = "week_workouts";
    private static final String TAG_TITLE_WORKOUT = "workout_title";
    private static final String TAG_MUSCLE_GROUPS = "muscle_groups";
    private static final String TAG_DATE_OF_WORKOUT = "date_of_workout";
    private static final String TAG_EXERCISES = "exercises";
    private static final String TAG_EXERCISE_TITLE = "exercise_title";
    private static final String TAG_EXERCISE_ROUNDS = "rounds";
    private static final String TAG_EXERCISE_REPETITIONS = "repetitions";

    private static final String TAG_WEEK_MENU = "week_menu";
    private static final String TAG_MENU_TITLE = "menu_title";
    private static final String TAG_MENU_DATE = "date_of_menu";
    private static final String TAG_MEALS = "meals";
    private static final String TAG_MEAL_FOOD_INTAKE = "food_intake";
    private static final String TAG_MEAL = "meal";

    public static void setWorkoutsFromJSON(Context context, WorkoutDao workoutDao, ExerciseDao exerciseDao, int fileId) throws IOException, JSONException {
        JSONArray jsonArrayWeekWorkouts = readJSONtoObject(context, fileId).getJSONArray(TAG_WEEK_WORKOUTS);
        JSONObject workoutObject;
        String workoutTitle;
        String muscleGroups;
        String dateOfWorkout;
        JSONObject exerciseObject;
        String exerciseTitle;
        int rounds;
        int repetitions;
        for (int i = 0; i < jsonArrayWeekWorkouts.length(); i++) {
            workoutObject = jsonArrayWeekWorkouts.getJSONObject(i);
            workoutTitle = workoutObject.getString(TAG_TITLE_WORKOUT);
            muscleGroups = workoutObject.getString(TAG_MUSCLE_GROUPS);
            dateOfWorkout = workoutObject.getString(TAG_DATE_OF_WORKOUT);
            long workoutId = workoutDao.insert(new Workout(User.getACTIVEUSER().getId(),
                    workoutTitle, muscleGroups, dateOfWorkout));

            JSONArray jsonArrayExercises = workoutObject.getJSONArray(TAG_EXERCISES);
            for (int a = 0; a < jsonArrayExercises.length(); a++) {
                exerciseObject = jsonArrayExercises.getJSONObject(a);
                exerciseTitle = exerciseObject.getString(TAG_EXERCISE_TITLE);
                rounds = exerciseObject.getInt(TAG_EXERCISE_ROUNDS);
                repetitions = exerciseObject.getInt(TAG_EXERCISE_REPETITIONS);
                exerciseDao.insert(new Exercise(workoutId,
                        exerciseTitle, rounds, repetitions));
            }
        }
    }

    public static void setMenuFromJSON(Context context, DailyMenuDao dailyMenuDao, MealDao mealDao, int fileId) throws IOException, JSONException {
        JSONArray jsonArrayMenu = readJSONtoObject(context, fileId).getJSONArray(TAG_WEEK_MENU);
        JSONObject menuObject;
        String menuTitle;
        String dateOfMenu;
        JSONObject mealObject;
        String foodIntake;
        String meal;
        for (int i = 0; i < jsonArrayMenu.length(); i++) {
            menuObject = jsonArrayMenu.getJSONObject(i);
            menuTitle = menuObject.getString(TAG_MENU_TITLE);
            dateOfMenu = menuObject.getString(TAG_MENU_DATE);
            long menuId = dailyMenuDao.insert(new DailyMenu(User.getACTIVEUSER().getId(),
                    menuTitle, dateOfMenu));

            JSONArray jsonArrayMeals = menuObject.getJSONArray(TAG_MEALS);
            for (int a = 0; a < jsonArrayMeals.length(); a++) {
                mealObject = jsonArrayMeals.getJSONObject(a);
                foodIntake = mealObject.getString(TAG_MEAL_FOOD_INTAKE);
                meal = mealObject.getString(TAG_MEAL);
                mealDao.insert(new Meal(menuId, foodIntake, meal));
            }
        }
    }

    private static JSONObject readJSONtoObject(Context context, int fileId) throws IOException, JSONException {
        InputStream input = context.getResources().openRawResource(fileId);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String jsonString;
        while ((jsonString = buffer.readLine()) != null) {
            builder.append(jsonString);
            builder.append("\n");
        }
        return new JSONObject(builder.toString());
    }
}
