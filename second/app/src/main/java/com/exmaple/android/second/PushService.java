package com.exmaple.android.second;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import static com.exmaple.android.second.MainActivity.randNum;
import static com.exmaple.android.second.MainActivity.uniqueID;


/**
 * Created by emmettna on 30/4/17.
 */

public class PushService extends Service {

    Control control = new Control();
    ArrayList<String> arr = control.getList();
    Random ran = new Random();
    Handler handler;
    Runnable runnable;
    static boolean pushOn = false;
    final int timer = 600000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("zzz","onBind");
        return null;
    }

    public int onStartCommand(Intent intent,int flags, int startId) {

        handler = new Handler();
        showNotification();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,timer);
                Log.d("zzz","10Mins Up!");
                _getRandNum();
                showNotification();
                if(!pushOn){
                    pushOn=true;
                }
            }
        };
        handler.postDelayed(runnable,timer);
        return START_NOT_STICKY;

    }
    public void onDestroy(){
        handler.removeCallbacks(runnable);
        pushOn = false;
    }
    public void showNotification(){
        Notification.Builder notification = new Notification.Builder(this);
        notification.setSmallIcon(R.drawable.iconimagecopy) //or you can replace with ic_launcher.png
                .setTicker("New Idiom Up")
                .setWhen(System.currentTimeMillis())
                .setContentTitle(arr.get(randNum))
                .setContentText(arr.get(randNum+1));

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingintent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingintent);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());

    }
    public void _getRandNum(){
        randNum = ran.nextInt(arr.size()-1);
        if(randNum%2!=0) randNum += 1;
    }

}
