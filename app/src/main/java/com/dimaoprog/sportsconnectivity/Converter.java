package com.dimaoprog.sportsconnectivity;

import android.arch.persistence.room.TypeConverter;
import android.databinding.InverseMethod;

import com.applandeo.materialcalendarview.EventDay;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;
import com.dimaoprog.sportsconnectivity.dbEntities.WorkoutEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Converter {

    @InverseMethod("stringToInt")
    public static String intToString(int value) {
        return value == 0 ? "" : String.valueOf(value);
    }

    public static int stringToInt(String value) {
        return value.isEmpty() ? 0 : Integer.valueOf(value);
    }

    @InverseMethod("stringToDouble")
    public static String doubleToString(double value) {
        return String.valueOf(value);
    }

    public static double stringToDouble(String value) {
        return Double.valueOf(value);
    }

    @TypeConverter
    public static Date longToDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long dateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String dateToString(Date date) {
        return date == null ? null : new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date);
    }

    public static int dateToDayInt(Date date) {
        return date == null ? null : Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(date));
    }

    public static WorkoutEvent workoutToEvent(Workout workout) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(workout.getDateOfWorkout());
        return new WorkoutEvent(calendar, workout.getWorkoutTitle(), workout.getId());
    }

    public static List<EventDay> workoutToEventList(List<Workout> workoutList) {
        if (workoutList != null) {
            List<EventDay> workoutEventList = new ArrayList<>();
            for (int i = 0; i < workoutList.size(); i++) {
                workoutEventList.add(workoutToEvent(workoutList.get(i)));
            }
            return workoutEventList;
        } else {
            return null;
        }
    }

}