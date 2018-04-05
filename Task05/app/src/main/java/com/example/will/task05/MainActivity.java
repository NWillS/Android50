package com.example.will.task05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FiveYearTeacher five1 = new FiveYearTeacher("five1", 23, true, 1000.0);
        FiveYearTeacher five2 = new FiveYearTeacher("five2", 22, false, 1010.0);
        TenYearTeacher ten1 = new TenYearTeacher("ten1",25,false,1100.0);
        TenYearTeacher ten2 = new TenYearTeacher("ten2",26,true,1110.0);

        Log.i("System.out/Five1",Double.toString(five1.calculateSalary()));
        Log.i("System.out/Five2",Double.toString(five2.calculateSalary()));
        Log.i("System.out/Ten1", Double.toString(ten1.calculateSalary()));
        Log.i("System.out/Ten2", Double.toString(ten2.calculateSalary()));
    }
}
