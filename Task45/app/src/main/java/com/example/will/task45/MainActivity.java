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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    // 更新時間(目安)
    private static final int LOCATION_UPDATE_MIN_TIME = 0;
    // 更新距離(目安)
    private static final int LOCATION_UPDATE_MIN_DISTANCE = 0;

    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLocationUpdates();
            }
        });
    }

    // Called when the location has changed.
    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged.");
        showLocation(location);
    }

    // Called when the provider status changed.
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e(TAG, "onStatusChanged.");
        showProvider(provider);
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                // if the provider is out of service, and this is not expected to change in the near future.
                String outOfServiceMessage = provider + " が圏外になっていて取得できません。";
                showMessage(outOfServiceMessage);
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                // if the provider is temporarily unavailable but is expected to be available shortly.
                String temporarilyUnavailableMessage = "一時的に " + provider + " が利用できません。もしかしたらすぐに利用できるようになるかもです。";
                showMessage(temporarilyUnavailableMessage);
                break;
            case LocationProvider.AVAILABLE:
                // if the provider is currently available.
                if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
                    String availableMessage = provider + " が利用可能になりました。";
                    showMessage(availableMessage);
                    requestLocationUpdates();
                }
                break;
            default:
                break;
        }
    }

    // Called when the provider is enabled by the user.
    @Override
    public void onProviderEnabled(String provider) {
        Log.e(TAG, "onProviderEnabled.");
        String message = provider + " が有効になりました。";
        showMessage(message);
        showProvider(provider);
        if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
            requestLocationUpdates();
        }
    }

    // Called when the provider is disabled by the user.
    @Override
    public void onProviderDisabled(String provider) {
        Log.e(TAG, "onProviderDisabled.");
        showProvider(provider);
        if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
            String message = provider + " が無効になってしまいました。";
            showMessage(message);
        }
    }

    private void requestLocationUpdates() {
        Log.e(TAG, "requestLocationUpdates()");
        showProvider(LocationManager.NETWORK_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        showNetworkEnabled(isNetworkEnabled);
        if (isNetworkEnabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION},1010);

                return;
            }
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    LOCATION_UPDATE_MIN_TIME,
                    LOCATION_UPDATE_MIN_DISTANCE,
                    this);
            Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                showLocation(location);
            }
        } else {
            String message = "Networkが無効になっています。";
            showMessage(message);
        }
    }

    private void showLocation(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        long time = location.getTime();
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS", Locale.JAPAN);
        String dateFormatted = formatter.format(date);

        Log.i("System.out",String.format("%s緯度：%f、経度：%f",dateFormatted,latitude,longitude));
    }

    private void showMessage(String message) {
        Log.i("System.out",message);
    }

    private void showProvider(String provider) {
        Log.i("System.out","Provider : " + provider);
    }

    private void showNetworkEnabled(boolean isNetworkEnabled) {
        Log.i("System.out","NetworkEnabled : " + String.valueOf(isNetworkEnabled));
    }
}