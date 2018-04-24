package com.example.will.task31.db;

import android.arch.persistence.room.TypeConverter;

import com.example.will.task31.api.model.Image;

public class Converter {
    @TypeConverter
    public static String toUrl(Image image){
        return image.getUrl();
    }

    @TypeConverter
    public static Image toImage(String url){
        Image image = new Image();
        image.setUrl(url);
        return image;
    }
}
