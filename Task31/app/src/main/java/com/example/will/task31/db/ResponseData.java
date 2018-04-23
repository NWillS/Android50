package com.example.will.task31.db;

import com.example.will.task31.ForecastData;

import java.util.List;

public class ResponseData {
    private String description;
    private List<ForecastData> forecastDataList;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ForecastData> getForecastDataList() {
        return this.forecastDataList;
    }

    public void setForecastDataList(List<ForecastData> forecastDataList) {
        this.forecastDataList = forecastDataList;
    }
}
