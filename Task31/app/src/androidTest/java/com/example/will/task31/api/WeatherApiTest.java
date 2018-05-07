package com.example.will.task31.api;

import com.example.will.task31.api.model.Weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static org.junit.Assert.*;

public class WeatherApiTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getWeather() {
        WeatherApi weatherApi =  new WeatherApi(new WeatherApi.WeatherApiCallback() {
            @Override
            public void success(Weather weather) {
                assertEquals(weather.getLocation().getCity(),"東京");
                assertEquals(weather.getForecasts().get(0).getDateLabel(),"今日");
            }

            @Override
            public void failed() {

            }
        });
        weatherApi.getWeather();
    }
}