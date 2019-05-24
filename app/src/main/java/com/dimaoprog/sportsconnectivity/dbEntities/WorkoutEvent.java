package com.dimaoprog.sportsconnectivity.dbEntities;

import com.applandeo.materialcalendarview.EventDay;
import com.dimaoprog.sportsconnectivity.R;

import java.util.Calendar;

public class WorkoutEvent extends EventDay {

    private String workoutTitle;
    private long workoutId;

    public WorkoutEvent(Calendar day, String workoutTitle, long workoutId) {
        super(day, R.drawable.icon_workout);
        this.workoutTitle = workoutTitle;
        this.workoutId = workoutId;
    }

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }
}
