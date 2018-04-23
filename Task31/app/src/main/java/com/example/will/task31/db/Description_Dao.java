package com.example.will.task31.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface Description_Dao {

    @Query("SELECT * FROM descriptionEntity ORDER BY uid DESC LIMIT 1")
    DescriptionEntity getNewest();

    @Insert
    void insertEntity(DescriptionEntity descriptionEntity);

}
