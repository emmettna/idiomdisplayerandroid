package com.exmaple.android.second;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

/**
 * Created by emmettna on 30/4/17.
 */

public class PushService extends IntentService {

    public static final String TAG = "com.exmapl.second";
    private  Handler handler;


    public PushService() {
        super("PushService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"the serv");
        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //something
                Log.d("zzz","run was called");
                System.out.println("dushidushi");

                handler.postDelayed(this,1000);
            }
        };
        handler. postDelayed(runnable, 1000);

    }
}
