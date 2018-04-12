package com.example.will.task24;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences data = getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();

        double d = 1234.567;

        editor.putInt("DataInt", 123);
        editor.putLong("DataLong", Double.doubleToRawLongBits(d));
        editor.putString("DataString", "sample");
        editor.apply();

        int dataInt = data.getInt("DataInt", 0);
        long dataLong = data.getLong("DataLong", 0);
        String dataString = data.getString("DataString", null);

        Log.i("System.out", "Int = " + dataInt + " \n" +
                        "Double = " + Double.longBitsToDouble(dataLong) + "\n" +
                        "String = " + dataString);
    }
}
