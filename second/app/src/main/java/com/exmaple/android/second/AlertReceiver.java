package com.exmaple.android.second;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by emmettna on 23/4/17.
 */

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        creatNotification(context, "Times up", "5 seconds Has passed", "alert");
    }
    public void creatNotification(Context context, String msg, String msgText, String msgAlert){
        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class),0);
        android.support.v4.app.NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ntt)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);
        mBuilder.setContentIntent(notificationIntent);

        mBuilder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1,mBuilder.build());
    }
}
