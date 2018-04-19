package com.example.will.task39;

import android.app.Application;
import android.graphics.Bitmap;

public class MyApplication extends Application {
    private Bitmap obj;

    public void setObj(Bitmap bmp){
        obj = null;
        obj = bmp;
    }

    public Bitmap getObj(){
        return obj;
    }

    public void clearObj(){
        obj = null;
    }

}
