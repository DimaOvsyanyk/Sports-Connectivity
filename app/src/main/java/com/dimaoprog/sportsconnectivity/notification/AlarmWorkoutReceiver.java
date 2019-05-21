package com.dimaoprog.sportsconnectivity.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.dimaoprog.sportsconnectivity.ForWorkoutsActivity;
import com.dimaoprog.sportsconnectivity.R;

public class AlarmWorkoutReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentToRepeat = new Intent(context, ForWorkoutsActivity.class);
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NotificationHelper.ALARM_TYPE_WORKOUT,
                intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = buildLocalNotif(context, pendingIntent).build();
        NotificationHelper.getNotificationManager(context).notify(NotificationHelper.ALARM_TYPE_WORKOUT,
                notification);
    }

    public NotificationCompat.Builder buildLocalNotif(Context context, PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon_workout)
                .setContentTitle("SportsConnectivity")
                .setContentText("Don't forget to workout today")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true);
    }

}
