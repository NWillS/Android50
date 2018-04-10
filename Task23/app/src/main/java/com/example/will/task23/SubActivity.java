package com.example.will.task23;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = this.getIntent();
        String text = intent.getStringExtra("textStr");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);
    }
}
