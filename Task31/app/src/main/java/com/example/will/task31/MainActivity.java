package com.example.will.task31;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.will.task31.api.LivedoorWeatherWebService;
import com.example.will.task31.api.model.Forecast;
import com.example.will.task31.api.model.Weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "VariableNotUsedInsideIf"})
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String API_URL = "http://weather.livedoor.com/forecast/webservice/json/";
    private final Handler handler = new Handler();
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

        getForecast();

    }

    private void getForecast(){
        try {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LivedoorWeatherWebService service = retrofit.create(LivedoorWeatherWebService.class);
                    Call<Weather> call = service.webservice("130010");

                    Weather weather = null;
                    try {
                        weather = call.execute().body();
                        if (weather != null) {
                            Log.d(TAG, "weather is not null");
                        } else {
                            Log.d(TAG, "weather is null");
                        }
                    } catch (IOException e) {
                        Log.d(TAG, "weather :" + e.getMessage());
                    }

                    final Weather temp_weather = weather;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (temp_weather != null) {
                                description.setText(temp_weather.description.text);
                                List<ForecastData> forecasts = new ArrayList<>();
                                for(int position = 0; position < temp_weather.forecasts.size();position++){
                                    Forecast forecast = temp_weather.forecasts.get(position);
                                    String date = forecast.dateLabel;
                                    String telop = forecast.telop;
                                    String iconUrl = forecast.image.url;

                                    ForecastData forecastData = new ForecastData(date,telop,iconUrl);
                                    forecasts.add(forecastData);
                                }

                                adapter.setForecasts(forecasts);
                                adapter.notifyDataSetChanged();

                            }
                        }
                    });
                }
            });

            thread.start();

        } catch (RuntimeException e) {
            Log.e("System.err",e.getMessage());
        }
    }


}