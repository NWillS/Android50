package com.example.will.task31.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.will.task31.Forecast;

import java.util.List;

@Dao
public interface Forecast_Dao {

    @Query("SELECT * FROM forecast ORDER BY date")
    List<Forecast> getNewest();

    @Insert
    void insertEntity(Forecast forecastEntity);

    @Query("DELETE FROM forecast")
    void deleteALL();

}
