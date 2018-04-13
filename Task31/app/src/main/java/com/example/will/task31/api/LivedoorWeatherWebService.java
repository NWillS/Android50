package com.example.will.task31.api;

import com.example.will.task31.api.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@SuppressWarnings("InterfaceNeverImplemented")
public interface LivedoorWeatherWebService {
    @GET("v1")
    Call<Weather> webservice(@Query("city") String city);
}