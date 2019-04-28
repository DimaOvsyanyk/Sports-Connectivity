package com.dimaoprog.sportsconnectivity.dbWorkouts;

import android.content.Context;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONWorkoutReader {
    private static final String TAG_WEEK_WORKOUTS = "week_workouts";
    private static final String TAG_TITLE_WORKOUT = "workout_title";
    private static final String TAG_MUSCLE_GROUPS = "muscle_groups";
    private static final String TAG_DATE_OF_WORKOUT = "date_of_workout";
    private static final String TAG_EXERCISES = "exercises";
    private static final String TAG_EXERCISE_TITLE = "exercise_title";
    private static final String TAG_EXERCISE_ROUNDS = "rounds";
    private static final String TAG_EXERCISE_REPETITIONS = "repetitions";

    public static void setWorkoutsFromJSON(Context context, WorkoutDao workoutDao, ExerciseDao exerciseDao, int fileId) throws IOException, JSONException {
        String jsonText = readJSON(context, fileId);
        JSONObject jsonObject = new JSONObject(jsonText);
        JSONArray jsonArrayWeekWorkouts = jsonObject.getJSONArray(TAG_WEEK_WORKOUTS);
        String workoutTitle;
        String muscleGroups;
        String dateOfWorkout;
        for (int i = 0; i < jsonArrayWeekWorkouts.length(); i++) {
            JSONObject workoutObject = jsonArrayWeekWorkouts.getJSONObject(i);
            workoutTitle = workoutObject.getString(TAG_TITLE_WORKOUT);
            muscleGroups = workoutObject.getString(TAG_MUSCLE_GROUPS);
            dateOfWorkout = workoutObject.getString(TAG_DATE_OF_WORKOUT);
            Workout newWorkout = new Workout(User.getACTIVEUSER().getId(),
                    workoutTitle, muscleGroups, dateOfWorkout);
            long workoutId = workoutDao.insert(newWorkout);

            JSONArray jsonArrayExercises = workoutObject.getJSONArray(TAG_EXERCISES);
            String exerciseTitle;
            int rounds;
            int repetitions;
            for (int a = 0; a < jsonArrayExercises.length(); a++) {
                JSONObject exerciseObject = jsonArrayExercises.getJSONObject(a);
                exerciseTitle = exerciseObject.getString(TAG_EXERCISE_TITLE);
                rounds = exerciseObject.getInt(TAG_EXERCISE_ROUNDS);
                repetitions = exerciseObject.getInt(TAG_EXERCISE_REPETITIONS);
                Exercise newExercise = new Exercise(workoutId, exerciseTitle,
                        rounds, repetitions);
                exerciseDao.insert(newExercise);
            }
        }
    }

    private static String readJSON(Context context, int fileId) throws IOException {
        InputStream input = context.getResources().openRawResource(fileId);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String jsonString;
        while ((jsonString = buffer.readLine()) != null) {
            builder.append(jsonString);
            builder.append("\n");
        }
        return builder.toString();
    }
}
