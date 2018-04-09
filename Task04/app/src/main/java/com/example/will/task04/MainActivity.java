package com.example.will.task04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Account> internship = new ArrayList<>();

        Account member01 = new Account("01",19,"man","Java");
        internship.add(member01);
        Account member02 = new Account("03",20,"woman","Swift");
        internship.add(member02);

        for(Account member : internship){
            Log.d("System.out/Info",member.getInfo());
        }

    }
}
