package com.example.will.task31.api.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Description {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {

        return this.uid;
    }
}
