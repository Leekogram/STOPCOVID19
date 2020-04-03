package com.aldroids.stopcovid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton30mins;
    private RadioButton radioButton1hour;
    private RadioButton radioButton2hours;
    private RadioButton radioButton3hours;
    private RadioButton radioButton6Hours;
    private RadioButton radioButton12hours;
    private RadioButton radioButton1day;
    private RadioButton radioButtonStop;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    String alertType ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUpWindowAnimations();
        mPrefs = getSharedPreferences("COVID-19 APP",MODE_PRIVATE);
        mEditor = mPrefs.edit();

        radioGroup = findViewById(R.id.radioGroup);
        radioButton1hour = findViewById(R.id._1Hour);
        radioButton2hours = findViewById(R.id._2Hours);
        radioButton3hours = findViewById(R.id._3Hours);
        radioButton6Hours = findViewById(R.id._6hours);
        radioButton12hours = findViewById(R.id._12Hours);
        radioButton1day = findViewById(R.id._1Day);
        radioButton30mins = findViewById(R.id._30min);
        radioButtonStop = findViewById(R.id._Stop);


        alertType = mPrefs.getString("alertStatus","30 Minutes");


        switch (alertType){

            case "30 Minutes":
              radioButton30mins.setChecked(true);
                break;
            case "One Hour":

                radioButton1hour.setChecked(true);
                break;
            case "Two Hours":

                radioButton12hours.setChecked(true);
                break;
            case  "3 Hours":

                radioButton3hours.setChecked(true);
                break;
            case "6 Hours":

                radioButton6Hours.setChecked(true);
                break;
            case "12 Hours":

                radioButton12hours.setChecked(true);
                break;
            case "One Day":

                radioButton1day.setChecked(true);
                break;

            case "Stop":

                radioButtonStop.setChecked(true);
                break;
                default:
                    radioButton1hour.setChecked(false);

                radioButton12hours.setChecked(false);

                radioButton3hours.setChecked(false);

                radioButton6Hours.setChecked(false);

                radioButton12hours.setChecked(false);

                radioButton1day.setChecked(false);

                radioButtonStop.setChecked(false);


        }


        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                     // Handle clicks here
                        RadioButton rb =  group.findViewById(checkedId);
                        switch (rb.getId()) {
                            case R.id._30min:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "30 Minutes";
                                mEditor.putString("alertStatus", alertType);
                                break;
                            case R.id._1Hour:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "One Hour";
                                mEditor.putString("alertStatus", alertType);
                                break;
                            case R.id._2Hours:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "Two Hours";
                                mEditor.putString("alertStatus", alertType);
                                break;
                            case R.id._3Hours:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "3 Hours";
                                mEditor.putString("alertStatus", alertType);
                                break;
                            case R.id._6hours:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "6 Hours";
                                mEditor.putString("alertStatus", alertType);
                                break;
                            case R.id._12Hours:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "12 Hours";
                                mEditor.putString("alertStatus", alertType);
                                break;
                            case R.id._1Day:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "One Day";
                                mEditor.putString("alertStatus", alertType);
                                break;
                            case R.id._Stop:
// Do something here
                                mEditor.remove("alertStatus");
                                alertType = "Stop";
                                mEditor.putString("alertStatus", alertType);
                                break;
                        }
                    }
                }
        );




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
    @Override
    public  void onResume(){
        super.onResume();


    }
    @Override
    protected void onPause() {
        super.onPause();
// Save the settings here
        mEditor.commit();
    }
    private void setUpWindowAnimations(){


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade enter_fade = new Fade();
            enter_fade.setDuration(2000);
            getWindow().setEnterTransition(enter_fade);

            Slide return_slide = new Slide();
            return_slide.setDuration(2000);
            getWindow().setReturnTransition(return_slide);


        }
    }
}
