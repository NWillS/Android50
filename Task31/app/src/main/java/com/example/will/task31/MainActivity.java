package com.example.will.task31;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.will.task31.api.LivedoorWeatherWebService;
import com.example.will.task31.api.model.Forecast;
import com.example.will.task31.api.model.Weather;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ForecastDialogFragment.ForecastDialogFragmentListener{
    public static final String TAG = "MainActivity";
    public static final String API_URL = "http://weather.livedoor.com/forecast/webservice/json/";
    private Handler handler = new Handler();

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        ForecastDialogFragment fd = new ForecastDialogFragment();
        fd.setListener(this);
        fd.show(getFragmentManager(),"forecast");
    }

    void getForecast(final int position){
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
                                if(temp_weather.forecasts.size() > position) {
                                    Forecast forecast = temp_weather.forecasts.get(position);
                                    String text = temp_weather.location.city + "の" +
                                            forecast.dateLabel + "の天気は" + forecast.telop + "です。";
                                    Log.i("System.out", text);
                                }
                                else {
                                    String text = temp_weather.location.city + "その日の天気はまだわかりません。";
                                    Log.i("System.out", text);
                                }
                            }
                        }
                    });
                }
            });

            thread.start();

        } catch (Exception e) {
            Log.e("System.err",e.getMessage());
        }
    }

    @Override
    public void onClicked(int position) {
        getForecast(position);
    }
}