package com.example.will.task31.db;


import android.os.AsyncTask;

import com.example.will.task31.MainActivity;
import com.example.will.task31.Forecast;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SelectTask extends AsyncTask<Void,Void,ResponseData> {

    private ForecastDatabase forecastDB;
    private WeakReference<MainActivity> activityReference;

    // only retain a weak reference to the activity
    public SelectTask(MainActivity context) {
        activityReference = new WeakReference<>(context);
    }


    @Override
    protected ResponseData doInBackground(Void... voids) {
        ResponseData res = new ResponseData();
        String description = activityReference.get()
                .getForecastDB()
                .description_dao()
                .getNewest()
                .getText();
        res.setDescription(description);


        List<Forecast> forecastEntities = activityReference.get().getForecastDB().forecast_dao().getNewest();
        res.setForecastList(forecastEntities);

        return res;
    }

    // onPostExecute runs on main thread
    @Override
    protected void onPostExecute(ResponseData responseData) {
        if (responseData != null){
            activityReference.get().selected(responseData);
        }
    }
}