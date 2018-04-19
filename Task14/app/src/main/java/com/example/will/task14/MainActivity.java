package com.example.will.task14;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements InputFragment.onClickListener{
    OutputFragment outputFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputFragment = (OutputFragment) getSupportFragmentManager().findFragmentById(R.id.outputFragment);;

    }

    @Override
    public void onClick(String text) {
        outputFragment.setTextView(text);
    }
}
