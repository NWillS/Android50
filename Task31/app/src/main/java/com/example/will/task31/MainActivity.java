package com.example.will.task31;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.will.task31.api.WeatherApi;
import com.example.will.task31.api.model.Forecast;
import com.example.will.task31.api.model.Weather;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,ForecastDialogFragment.ForecastDialogFragmentListener,WeatherApi.WeatherApiCallback{

    private Handler handler = new Handler();

    private WeatherApi weatherApi;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        weatherApi = new WeatherApi(this);
    }


    @Override
    public void onClick(View view) {
        ForecastDialogFragment fd = new ForecastDialogFragment();
        fd.setListener(this);
        fd.show(getFragmentManager(),"forecast");
    }

    void getForecast(){
        weatherApi.getWeather();
    }

    @Override
    public void onClicked(int position) {
        this.position = position;
        getForecast();
    }

    @Override
    public void success(Weather weather) {
        if (weather != null) {
            if(weather.getForecasts().size() > position) {
                Forecast forecast = weather.getForecasts().get(position);
                String text = weather.getLocation().getCity() + 'の' +
                        forecast.getDateLabel() + "の天気は" + forecast.getTelop() + "です。";
                Log.i("System.out", text);
            }
            else {
                String text = weather.getLocation().getCity() + "その日の天気はまだわかりません。";
                Log.i("System.out", text);
            }
        }
    }

    @Override
    public void failed() {

    }
}