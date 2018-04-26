package com.example.will.task31.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.will.task31.Forecast;
import com.example.will.task31.api.model.Description;
import com.example.will.task31.api.model.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {
    private ForecastDatabase forecastDatabase;
    private Description_Dao description_dao;
    private Forecast_Dao forecast_dao;
    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        forecastDatabase  = Room.databaseBuilder(context,
                ForecastDatabase.class, "forecastDatabase").build();
        description_dao = forecastDatabase.description_dao();
        description_dao.deleteALL();
        forecast_dao = forecastDatabase.forecast_dao();
        forecast_dao.deleteALL();
    }

    @After
    public void tearDown() throws Exception {
        forecastDatabase.close();
    }

    @Test
    public void insertAndSelect(){

        Forecast forecast = new Forecast();
        forecast.setTelop("晴れのち曇り");
        forecast_dao.insertEntity(forecast);

        List<Forecast> forecastList = forecast_dao.getNewest();
        Forecast selectedForecast = forecastList.get(0);
        assertEquals(selectedForecast.getTelop(),forecast.getTelop());

        Description description = new Description();
        description.setText("test");
        description_dao.insertEntity(description);

        Description selectedDescription = description_dao.getNewest();
        assertEquals(selectedDescription.getText(),description.getText());
    }
}