package com.example.will.task31.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ForecastEntity {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private String getDate;
    private String dateLabel;
    private String image;
    private String telop;

    public long getUid() {
        return this.uid;
    }

    public String getGetDate() {
        return this.getDate;
    }

    public String getDateLabel() {
        return this.dateLabel;
    }

    public String getImage() {
        return this.image;
    }

    public String getTelop() {
        return this.telop;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTelop(String telop) {
        this.telop = telop;
    }
}
