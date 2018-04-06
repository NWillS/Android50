package com.example.will.task14;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Input input = new Input();
        final Output output = new Output();


//        input.setListener(new Input.InputFragmentListener() {
//            @Override
//            public void buttonOnClick(String text) {
//                output.setTextView(text);
//            }
//        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.linear, input);
        transaction.add(R.id.linear, output);
        transaction.commit();
    }
}
