package com.example.will.task06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

@SuppressWarnings("MagicCharacter")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Designer designer1 = new Designer("designer1",23,true,"111-1111", 1000.0,3);
        Designer designer2 = new Designer("designer2",24,false,"222-2222", 2000.0,4);

        Programmer programmer1 = new Programmer("programmer1", 26, true,"333-3333",1000.0, 4,'A');
        Programmer programmer2 = new Programmer("programmer2", 27, false,"444-4444",2000.0, 5,'C');

        Log.i("System.out/Designer1", Double.toString(designer1.computeYearlyPay()));
        Log.i("System.out/Designer2", Double.toString(designer2.computeYearlyPay()));
        Log.i("System.out/Programmer1", Double.toString(programmer1.computeYearlyPay()));
        Log.i("System.out/Programmer2", Double.toString(programmer2.computeYearlyPay()));
    }
}
