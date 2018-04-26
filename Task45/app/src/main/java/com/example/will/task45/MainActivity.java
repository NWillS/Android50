package com.example.will.task45;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private boolean isUpdating;
    private Button button;

    private GPSChecker gpsChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        gpsChecker = new GPSChecker(this);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating) {
                    button.setText("START");
                    isUpdating = false;
                    stopGPS();
                } else {
                    button.setText("STOP");
                    isUpdating = true;
                    startGPS();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setText("START");
        isUpdating = false;
    }

    @Override
    protected void onPause() {
        stopGPS();
        super.onPause();
    }

    private void stopGPS(){
        gpsChecker.stopUsingGPS();
    }

    private void startGPS(){
        if(gpsChecker.canGetLocation){
            double latitude = gpsChecker.getLatitude();
            double longitude = gpsChecker.getLongitude();

            Log.i("System.out", String.format("緯度：%f、経度：%f", latitude, longitude));
        }
    }

}