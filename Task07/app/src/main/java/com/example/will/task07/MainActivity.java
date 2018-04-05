package com.example.will.task07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Result {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Math math = new Math(this);

        math.calculate();
    }

    @Override
    public void result(Integer resultValue) {
        Log.i("System.out/Result",Integer.toString(resultValue));
    }
}
