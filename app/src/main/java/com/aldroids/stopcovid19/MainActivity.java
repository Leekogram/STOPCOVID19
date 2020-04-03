package com.aldroids.stopcovid19;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.os.Handler;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    public static final String NOTIFICATION_REPLY = "NotificationReply";
    public static final String CHANNNEL_ID = "SimplifiedCodingChannel";
    public static final String CHANNEL_NAME = "COVID-19 Alert";
    public static final String CHANNEL_DESC = "Remember to wash your hands";

    public static final String KEY_INTENT_MORE = "keyintentmore";
    public static final String KEY_INTENT_HELP = "keyintenthelp";

    public static final int REQUEST_CODE_MORE = 100;
    public static final int REQUEST_CODE_HELP = 101;
    public static final int NOTIFICATION_ID = 200;
    private VideoView vid;
    private MediaController m;
    private  Uri u;
    String path;
    TextView HyperLink1, HyperLink2, HyperLink3, HyperLink4, HyperLink5;
    Spanned Text;
    private static  int TIME_OUT = 7000;

    private SharedPreferences mPrefs;
    String alertType ;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpWindowAnimations();

        mPrefs = getSharedPreferences("COVID-19 APP",MODE_PRIVATE);
        alertType = mPrefs.getString("alertStatus","30 Minutes");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(CHANNEL_DESC);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }



        HyperLink1 = findViewById(R.id.source1);
        HyperLink2 = findViewById(R.id.source2);
        HyperLink3 = findViewById(R.id.source3);
        HyperLink4 = findViewById(R.id.source4);
        HyperLink5 = findViewById(R.id.source5);

        Text = Html.fromHtml("Click on this link to visit Source <br />" +
                "<a href='https://www.who.int/emergencies/diseases/novel-coronavirus-2019'>World Health Organization</a>");
        Spanned Text1 = Html.fromHtml("Click on this link to visit Source <br />" +
                "<a href='https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public/myth-busters'>World Health Organization</a>");

        HyperLink1.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink1.setText(Text);
        HyperLink2.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink2.setText(Text);
        HyperLink3.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink3.setText(Text);
        HyperLink4.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink4.setText(Text);
        HyperLink5.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink5.setText(Text1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        vid = findViewById(R.id.videoPlayer);
        m = new MediaController(this);
        vid.setMediaController(m);
         path = "android.resource://com.aldroids.stopcovid19/"+R.raw.novelcoronavirus;



AsyncTaskRunner runner = new AsyncTaskRunner();
runner.execute();

         calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.About:
                        Intent a = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.Settings:
                        Intent s = new Intent(getApplicationContext(), Settings.class);
                        startActivity(s);
                        break;
                    case R.id.More:
                        Intent m = new Intent(getApplicationContext(), More.class);
                        startActivity(m);

                        
                }
                return true;
            }
        });
    }


    public  void getVideo(){
        u = Uri.parse(path);
        vid.setVideoURI(u);
    }
        private class AsyncTaskRunner extends AsyncTask<Void,Void,Void> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params){

               getVideo();
                return null;
            }

            @Override
            protected void onPostExecute(Void result){
                super.onPostExecute(result);

            }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public  void onResume(){
        super.onResume();

        mPrefs = getSharedPreferences("COVID-19 APP",MODE_PRIVATE);
        alertType = mPrefs.getString("alertStatus","30 Minutes");


        switch (alertType){
            case "30 Minutes":

                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        1000 * 60 *30, alarmIntent);
                break;
            case "One Hour":

                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent onehourintent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, onehourintent, 0);
               // alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                 //       1000 * 60 * 60  , alarmIntent);
                alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime()+AlarmManager.INTERVAL_HOUR,
                        AlarmManager.INTERVAL_HOUR,alarmIntent);
                break;
            case "Two Hours":

                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent twohoursintent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, twohoursintent, 0);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        1000 * 60 * 60 * 2, alarmIntent);
                break;
            case  "3 Hours":

                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent threehoursintent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, threehoursintent, 0);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        1000 * 60 * 60 * 3, alarmIntent);
                break;
            case "6 Hours":

                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent sixhoursintent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, sixhoursintent, 0);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        1000 * 60 * 60 * 6, alarmIntent);
                break;
            case "12 Hours":

                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent twelvehoursintent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, twelvehoursintent, 0);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        1000 * 60 * 60 * 12, alarmIntent);
                break;
            case "One Day":

                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent onedayintent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, onedayintent, 0);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        1000 * 60 * 60 * 24, alarmIntent);
                break;

            case "Stop":
                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent cancelintent = new Intent(getApplicationContext(), NotificationReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, cancelintent, 0);
                if (alarmMgr!=null){
                    alarmMgr.cancel(alarmIntent);
                }
                break;


        }

    }
    private void setUpWindowAnimations(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(1000);
            getWindow().setReenterTransition(slide);
            getWindow().setExitTransition(slide);
        }
    }
}
