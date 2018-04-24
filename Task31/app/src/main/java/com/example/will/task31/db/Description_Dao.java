package com.example.will.task31.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.will.task31.api.model.Description;

@Dao
public interface Description_Dao {

    @Query("SELECT * FROM description ORDER BY uid DESC LIMIT 1")
    Description getNewest();

    @Insert
    void insertEntity(Description descriptionEntity);

}
