package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class WorkoutsRepository {

    private WorkoutDao workoutDao;
    private ExerciseDao exerciseDao;

    private LiveData<List<Workout>> allWorkouts;
    private LiveData<List<Exercise>> allExercises;

    public static final int FIRST_WEEK_WORKOUTS = R.raw.week_workouts;

    public WorkoutsRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        workoutDao = database.workoutDao();
        exerciseDao = database.exerciseDao();

        allWorkouts = workoutDao.getByUserId(User.getACTIVEUSER().getId());
    }


    public long insert(Workout workout) {
        return workoutDao.insert(workout);
    }

    public void update(Workout workout) {
        workoutDao.update(workout);
    }

    public void delete(Workout workout) {
        workoutDao.delete(workout);
    }

    public Workout getWorkoutById(long workoutId) {
        return workoutDao.getById(workoutId);
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public void insert(Exercise exercise) {
        exerciseDao.insert(exercise);
    }

    public void update(Exercise exercise) {
        exerciseDao.update(exercise);
    }

    public void delete(Exercise exercise) {
        exerciseDao.delete(exercise);
    }

    public LiveData<List<Exercise>> getAllExercises(long workoutId) {
        allExercises = exerciseDao.getByWorkoutId(workoutId);
        return allExercises;
    }

    public List<Exercise> getAllExercisesStaticList(long workoutId) {
        return exerciseDao.getByWorkoutIdStaticList(workoutId);
    }

    public void addWorkoutsFromJson(Context context) throws IOException, JSONException {
        JSONReader.setWorkoutsFromJSON(context, workoutDao, exerciseDao, FIRST_WEEK_WORKOUTS);
    }
}
