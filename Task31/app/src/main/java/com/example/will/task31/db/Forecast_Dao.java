package com.example.will.task31.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Forecast_Dao {

    @Query("SELECT * FROM forecastEntity WHERE getDate = :getDate ORDER BY getDate desc")
    List<ForecastEntity> getNewest(String getDate);

    @Insert
    void insertEntity(ForecastEntity forecastEntity);

    @Query("DELETE FROM forecastEntity")
    void deleteALL();

}
