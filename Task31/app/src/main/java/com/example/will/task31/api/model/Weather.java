package com.example.will.task31.api.model;

import java.util.List;

public class Weather {
    private List<Forecast> forecasts;
    private Location location;

    public List<Forecast> getForecasts() {
        return this.forecasts;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
