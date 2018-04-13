package com.example.will.task31.api.model;

import java.util.List;

@SuppressWarnings("PublicField")
public class Weather {
    public List<Forecast> forecasts;
    public Location location;
    public Description description;
}
