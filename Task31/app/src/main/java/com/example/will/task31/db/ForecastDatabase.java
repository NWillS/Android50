package com.example.will.task31.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DescriptionEntity.class,ForecastEntity.class},version = 1,exportSchema = false)
public abstract class ForecastDatabase extends RoomDatabase {
    public abstract Description_Dao description_dao();
    public abstract Forecast_Dao forecast_dao();
}
