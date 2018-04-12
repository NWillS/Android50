package com.example.will.task23;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

@SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
public class SubActivity extends AppCompatActivity {

    public static final String TEXT_STR = "textStr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        String text = intent.getStringExtra(TEXT_STR);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);
    }
}
