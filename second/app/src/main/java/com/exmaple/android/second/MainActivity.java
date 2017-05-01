package com.exmaple.android.second;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Control control = new Control();
    ArrayList<String> arr = control.getList();
    TextView engIdiom;
    TextView korIdiom;
    CheckBox checkBox;
    Random ran = new Random();
    int randNum;
    ArrayList<Integer> stack = new ArrayList<>();
    Intent pushIntent;
    NotificationCompat.Builder notification;
    private static final int uniqueID= (int) new Date().getTime();
    private int timer = 1000;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        disabling Notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        engIdiom = (TextView) findViewById(R.id.engIdiom);
        korIdiom = (TextView) findViewById(R.id.korIdiom);
        checkBox = (CheckBox) findViewById(R.id.stayOnLockScreen);
        _getRandNum();
        setTexts();
        push();
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        pushIntent = new Intent(this,PushService.class);

    }
    public void nextIdiom(){
        _getRandNum();
        setTexts();
        push();
    }
    public void onNextButtonClick(View view) {
        nextIdiom();
//        Log.d("zzz","Got to nextIdiom before checkbox");
        if ( checkBox.isChecked() ) {
//            Log.d("zzz","inside if statement");
            showNotification();
        }
    }

    public void onPreviousButtonClick(View view) {
        if(stack.size()!=0){
        randNum = stack.get(stack.size()-1);
        setTexts();
        pop();
        }
    }

    public void _getRandNum(){
        randNum = ran.nextInt(arr.size()-1);
        if(randNum%2!=0) randNum += 1;
    }

    public void setTexts(){
        if(randNum%2!=0) randNum +=1;
        engIdiom.setText(arr.get(randNum));
        korIdiom.setText(arr.get(randNum + 1));
    }

    public void push(){
        if(stack.size() < 21){
            stack.add(randNum);
        }else {
            stack.remove(stack.get(0));
            stack.add(randNum);
        }
    }

    public void pop(){
        if(stack.size()>1) {
            stack.remove(stack.size() - 1);
        }
    }

    public void showNotification(){

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
    /* Currently timer is 10 minutes base but can add setTime button*/

    public void stayOnLockScreen(View view) {
        if ( ((CheckBox)view).isChecked() ) {
            showNotification();
            notification.setAutoCancel(false);
//            startService(pushIntent);

            timer = 100000;
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    showNotification();
                    handler.postDelayed(this,timer);
                    nextIdiom();
                    showNotification();
                }
            };
            handler.postDelayed(runnable,timer);
        }
        else{
            notification.setAutoCancel(true);
//            stopService(pushIntent);
            handler.removeCallbacks(runnable);
        }
    }
}
