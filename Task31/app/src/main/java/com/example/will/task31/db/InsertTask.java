package com.example.will.task31.db;


import android.os.AsyncTask;

import com.example.will.task31.Forecast;
import com.example.will.task31.MainActivity;
import com.example.will.task31.api.model.Description;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class  InsertTask extends AsyncTask<Void,Void,Boolean> {

    private WeakReference<MainActivity> activityReference;
    private Description description;
    private List<Forecast> forecastEntities;

    // only retain a weak reference to the activity


    public InsertTask(MainActivity context, Description description, List<Forecast> forecastEntities) {
        this.activityReference = new WeakReference<>(context);
        this.description = description;
        this.forecastEntities = forecastEntities;
    }

    // doInBackground methods runs on a worker thread
    @Override
    protected Boolean doInBackground(Void... objs) {
        // retrieve auto incremented note id

        activityReference.get().getForecastDB().forecast_dao().deleteALL();
        activityReference.get().getForecastDB().description_dao().insertEntity(description);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
        String nowDate = sdf.format(Calendar.getInstance().getTime());


        for(Forecast forecastEntity : forecastEntities){
            forecastEntity.setDate(nowDate);
            activityReference.get().getForecastDB().forecast_dao().insertEntity(forecastEntity);
        }
        return true;
    }

    // onPostExecute runs on main thread
    @Override
    protected void onPostExecute(Boolean bool) {
        if (bool){
            activityReference.get().changedDB();
        }
    }
}