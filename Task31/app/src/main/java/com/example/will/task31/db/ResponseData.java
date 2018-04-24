package com.example.will.task31.db;

import com.example.will.task31.Forecast;

import java.util.List;

public class ResponseData {
    private String description;
    private List<Forecast> forecastList;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Forecast> getForecastDataList() {
        return this.forecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }
}
