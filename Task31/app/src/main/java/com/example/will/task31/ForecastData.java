package com.example.will.task31;

import android.graphics.Bitmap;

public class ForecastData {

    private String date;
    private String forecast;
    private String iconURl;

    public ForecastData(String date, String forecast, String iconURl) {
        this.date = date;
        this.forecast = forecast;
        this.iconURl = iconURl;
    }

    public String getDate() {
        return this.date;
    }

    public String getForecast() {
        return this.forecast;
    }

    public String getIconURl() {
        return this.iconURl;
    }

}
