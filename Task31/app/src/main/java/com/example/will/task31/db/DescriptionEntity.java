package com.example.will.task31.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DescriptionEntity {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private String description;

    public long getUid() {
        return this.uid;
    }

    public String getDescription() {
        return this.description;
    }


    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
