package com.example.will.task31.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import java.util.List;

@Entity
public class Weather {
    @PrimaryKey
    public int uid;

    public String description;

    @Relation(parentColumn = "uid",entityColumn = "wid")
    public List<Forecast> forecasts;

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.


}