package com.aldroids.stopcovid19;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class Foreverservice extends Service {

    public Foreverservice(){

    }

    @Override
    public IBinder onBind(Intent intent){
        return  null;
    }

    @Override
    public  int onStartCommand(Intent intent, int flags, int startid){
        super.onStartCommand(intent, flags, startid);
        return START_STICKY;
    }
    @Override
    public void  onDestroy(){
        super.onDestroy();
        Intent foreverserviceintent = new Intent("RestartService");
        sendBroadcast(foreverserviceintent);
    }


}
