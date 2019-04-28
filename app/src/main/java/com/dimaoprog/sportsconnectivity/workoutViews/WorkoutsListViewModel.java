package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbWorkouts.WorkoutsRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class WorkoutsListViewModel extends AndroidViewModel {

    private WorkoutsRepository workoutsRep;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutsListViewModel(@NonNull Application application) {
        super(application);
        workoutsRep = new WorkoutsRepository(application);
        allWorkouts = workoutsRep.getAllWorkouts();
    }

    public void delete(Workout workout) {
        workoutsRep.delete(workout);
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public void addNewWorkoutsFromJson() throws IOException, JSONException {
        workoutsRep.addWorkoutsFromJson(getApplication());
    }
}
