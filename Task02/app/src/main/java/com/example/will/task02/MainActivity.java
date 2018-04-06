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
        for (String str:arr) {
            Log.d("logTestArr", str);
        }

        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        for(String key:map.keySet()){
            Log.d("logTestMap/Key",key);
        }
        for(Integer num:map.values()){
            Log.d("logTestMap/Values",num.toString());
        }
        map.put("two",4);
        Log.d("logTestMap/Two","two = " + map.get("two"));

        HashSet<String> set = new HashSet<>();
        set.add("A");
        set.add("A");
        set.add("A");
        set.add("B");
        set.add("B");
        set.add("C");

        Log.d("logTestSet/Num","set.num = " + set.size());
        for(String str:set){
            Log.d("logTestSet",str);
        }

    }
}
