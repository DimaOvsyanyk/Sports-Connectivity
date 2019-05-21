package com.dimaoprog.sportsconnectivity.notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;

public class NotificationHelper {

    private static final int NOTIF_WORKOUT_HOUR = 22;
    private static final int NOTIF_WORKOUT_MIN = 25;

    public static int ALARM_TYPE_WORKOUT = 100;
    private static AlarmManager alarmManagerWorkout;
    private static PendingIntent alarmIntentWorkout;

    public static void workoutNotification(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, NOTIF_WORKOUT_HOUR);
        calendar.set(Calendar.MINUTE, NOTIF_WORKOUT_MIN);
        calendar.set(Calendar.SECOND, 0);

        Log.d(LOG_MAIN, String.valueOf(calendar.getTimeInMillis()) + " "+
                calendar.getTime().toString());
        Intent intent = new Intent(context, AlarmWorkoutReceiver.class);
        alarmIntentWorkout = PendingIntent.getBroadcast(context, ALARM_TYPE_WORKOUT,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerWorkout = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManagerWorkout.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntentWorkout);
        Log.d(LOG_MAIN, "workoutNotification done");
    }

    public static void cancelAlarmWorkout() {
        if (alarmManagerWorkout != null) {
            alarmManagerWorkout.cancel(alarmIntentWorkout);
            Log.d(LOG_MAIN, "cancelAlarmWorkout done");
        }
        Log.d(LOG_MAIN, "cancelAlarmWorkout passed");
    }

    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void enableBootWorkoutReceiver(Context context){
        ComponentName receiver = new ComponentName(context, AlarmBootWorkoutReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Log.d(LOG_MAIN, "enableBootWorkoutReceiver done");
    }

    public static void disableBootWorkoutReceiver(Context context){
        ComponentName receiver = new ComponentName(context, AlarmBootWorkoutReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Log.d(LOG_MAIN, "disableBootWorkoutReceiver done");
    }

    public static boolean isAlarmManagerWorkoutOn() {
        return alarmManagerWorkout != null;
    }

}
