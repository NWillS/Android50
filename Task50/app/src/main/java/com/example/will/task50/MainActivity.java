package com.example.will.task50;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

@SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)){
            Uri uri = intent.getData();
            if (uri == null) {
                throw new AssertionError();
            }
            StringBuilder text = new StringBuilder("");
            for(String key : uri.getQueryParameterNames()){
                text.append(String.format("%s = %s\n",key,uri.getQueryParameter(key)));
            }
            textView.setText(text.toString());
        }

    }
}
