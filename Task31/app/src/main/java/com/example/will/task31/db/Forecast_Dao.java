package com.example.will.task31.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.will.task31.Forecast;

import java.util.List;

@Dao
public interface Forecast_Dao {

    @Query("SELECT * FROM forecast WHERE date = :date ORDER BY date desc")
    List<Forecast> getNewest(String date);

    @Insert
    void insertEntity(Forecast forecastEntity);

    @Query("DELETE FROM forecast")
    void deleteALL();

}
