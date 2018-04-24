package com.example.will.task31.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.will.task31.Forecast;
import com.example.will.task31.api.model.Description;

@Database(entities = {Description.class,Forecast.class},version = 1,exportSchema = false)
@TypeConverters({Converter.class})
public abstract class ForecastDatabase extends RoomDatabase {
    public abstract Description_Dao description_dao();
    public abstract Forecast_Dao forecast_dao();
}
