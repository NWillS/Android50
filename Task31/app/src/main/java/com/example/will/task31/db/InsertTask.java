package com.example.will.task31.db;


import android.os.AsyncTask;

import com.example.will.task31.MainActivity;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class  InsertTask extends AsyncTask<Void,Void,Boolean> {

    private WeakReference<MainActivity> activityReference;
    private DescriptionEntity descriptionEntity;
    private List<ForecastEntity> forecastEntities;

    // only retain a weak reference to the activity


    public InsertTask(MainActivity context, DescriptionEntity descriptionEntity, List<ForecastEntity> forecastEntities) {
        this.activityReference = new WeakReference<>(context);
        this.descriptionEntity = descriptionEntity;
        this.forecastEntities = forecastEntities;
    }

    // doInBackground methods runs on a worker thread
    @Override
    protected Boolean doInBackground(Void... objs) {
        // retrieve auto incremented note id
        activityReference.get().getForecastDB().description_dao().insertEntity(descriptionEntity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
        String nowDate = sdf.format(Calendar.getInstance().getTime());

        activityReference.get().getForecastDB().forecast_dao().deleteALL();

        for(ForecastEntity forecastEntity : forecastEntities){
            forecastEntity.setGetDate(nowDate);
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