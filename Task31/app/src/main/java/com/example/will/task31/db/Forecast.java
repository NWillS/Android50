package com.example.will.task31.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Forecast {
    @PrimaryKey
    public int fid;
    public int wid;
    public String label;
    public String telop;
    public String iconUrl;
}
