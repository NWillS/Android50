package com.example.will.task31.api.model;

import com.example.will.task31.Forecast;

import java.util.List;

public class Weather {
    private List<Forecast> forecasts;
    private Location location;
    private Description description;

    public List<Forecast> getForecasts() {
        return this.forecasts;
    }

    public Location getLocation() {
        return this.location;
    }

    public Description getDescription() {
        return this.description;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}