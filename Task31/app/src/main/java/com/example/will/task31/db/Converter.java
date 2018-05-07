package com.example.will.task31.db;

import android.arch.persistence.room.TypeConverter;

import com.example.will.task31.api.model.Image;

public class Converter {
    @TypeConverter
    public static String toUrl(Image image){
        return image == null ? null : image.getUrl();
    }

    @TypeConverter
    public static Image toImage(String url){
        if(url == null){
            return null;
        }
        Image image = new Image();
        image.setUrl(url);
        return image;
    }
}
