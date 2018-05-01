package com.example.will.task47;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printToken();

    }

    private void printToken(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("notification", refreshedToken);
    }
}
