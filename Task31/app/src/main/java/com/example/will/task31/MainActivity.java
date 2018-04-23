package com.example.will.task31;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.example.will.task31.api.WeatherApi;
import com.example.will.task31.api.model.Forecast;
import com.example.will.task31.api.model.Weather;
import com.example.will.task31.db.ForecastDatabase;
import com.example.will.task31.db.DescriptionEntity;
import com.example.will.task31.db.ForecastEntity;
import com.example.will.task31.db.InsertTask;
import com.example.will.task31.db.ResponseData;
import com.example.will.task31.db.SelectTask;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements WeatherApi.WeatherApiCallback{

    private WeatherApi weatherApi;
    private int position;
    private ForecastRecyclerViewAdapter adapter;
    private TextView description;
    private ForecastDatabase forecastDB;

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
        forecastDB = Room.databaseBuilder(getApplicationContext(),
                ForecastDatabase.class, "forecastDatabase").build();
        getForecast();
    }


    private void getForecast(){
        weatherApi.getWeather();
    }


    @Override
    public void success(Weather weather) {
        if (weather != null) {
            List<Forecast> forecasts = weather.getForecasts();

            DescriptionEntity descriptionEntity = new DescriptionEntity();
            descriptionEntity.setDescription(weather.getDescription().getText());
            List<ForecastEntity> forecastEntities = new ArrayList<>();
            for(Forecast forecast : forecasts){
                ForecastEntity entity = new ForecastEntity();
                entity.setDateLabel(forecast.getDateLabel());
                entity.setTelop(forecast.getTelop());
                entity.setImage(forecast.getImage().getUrl());

                forecastEntities.add(entity);
            }
            InsertTask insertTask = new InsertTask(this, descriptionEntity, forecastEntities);


            insertTask.execute();
        }
    }

    @Override
    public void failed() {
        Log.d("System.err", "error");
    }

    public ForecastDatabase getForecastDB() {
        return this.forecastDB;
    }

    public void changedDB(){
        Log.i("System.out","changedDB");
        SelectTask selectTask = new SelectTask(this);
        selectTask.execute();
    }

    public void selected(ResponseData res){
        Log.i("System.out","selected");

        description.setText(res.getDescription());
        adapter.setForecasts(res.getForecastDataList());
        adapter.notifyDataSetChanged();
    }
}