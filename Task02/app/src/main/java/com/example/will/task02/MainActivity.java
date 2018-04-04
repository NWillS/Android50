package com.example.will.task02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> arr = new ArrayList<>();
        arr.add("Hello");
        arr.add("World!");
        arr.add("123");
        Log.d("logTestArr","arr = " + arr);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        Log.d("logTestMap","map = " + map);

        HashSet<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        Log.d("logTestSet","set = " + set);
    }
}
