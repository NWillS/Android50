package com.example.will.task31;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.will.task31.api.WeatherApi;
import com.example.will.task31.api.model.Forecast;
import com.example.will.task31.api.model.Weather;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements WeatherApi.WeatherApiCallback{

    private WeatherApi weatherApi;
    private int position;
    private ForecastRecyclerViewAdapter adapter;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        description = (TextView) findViewById(R.id.descriptionTextView);
        adapter = new ForecastRecyclerViewAdapter();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        weatherApi = new WeatherApi(this);
        getForecast();
    }


    void getForecast(){
        weatherApi.getWeather();
    }


    @Override
    public void success(Weather weather) {
        if (weather != null) {
            description.setText(weather.getDescription().getText());
            List<Forecast> forecasts = weather.getForecasts();
            List<ForecastData> forecastDataList = new ArrayList<>();
            for (Forecast forecast : forecasts){
                ForecastData data = new ForecastData(forecast.getDateLabel(),forecast.getTelop(),forecast.getImage().getUrl());
                forecastDataList.add(data);
            }
            adapter.setForecasts(forecastDataList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed() {

    }
}