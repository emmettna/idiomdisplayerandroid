package com.exmaple.android.second;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Control control = new Control();
    ArrayList<String> arr = control.getList();
    TextView engIdiom;
    TextView korIdiom;
    Random ran = new Random();
    int randNum;
    ArrayList<Integer> stack = new ArrayList<>();


    NotificationCompat.Builder nnotification;
    private static final int uniqueID= 3414;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        engIdiom = (TextView) findViewById(R.id.engIdiom);
        korIdiom = (TextView) findViewById(R.id.korIdiom);
        _getRandNum();
        setTexts();
        push();
        nnotification = new NotificationCompat.Builder(this);
        nnotification.setAutoCancel(true);
    }

    public void onNextButtonClick(View view) {
        _getRandNum();
        setTexts();
        push();
        System.out.println(arr.get(randNum));

    }

    public void onPreviousButtonClick(View view) {
        if(stack.size()!=0){
        randNum = stack.get(stack.size()-1);
        setTexts();
        pop();
        }
        System.out.println(randNum);
    }



    public void _getRandNum(){
        randNum = ran.nextInt(arr.size()-1);
        if(randNum%2!=0) randNum += 1;
    }

    public void setTexts(){
        System.out.println("ran num is "+randNum);
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

    public void showNotification(View view){

        nnotification.setSmallIcon(R.drawable.ntt) //or you can replace with ic_launcher.png
            .setTicker("Gdaymate")
            .setWhen(System.currentTimeMillis())
            .setContentTitle("Here is the Title")
            .setContentText("this is the main text");

        Intent iintent = new Intent(this, MainActivity.class);
        PendingIntent ppendingintent = PendingIntent.getActivity(this,0,iintent,PendingIntent.FLAG_UPDATE_CURRENT);
        nnotification.setContentIntent(ppendingintent);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, nnotification.build());

    }
    public void setAlarm(View view){
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;
        Intent alertIntent = new Intent(this, AlertReceiver.class);
        AlarmManager alarmManager = (AlarmManager)
                getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
