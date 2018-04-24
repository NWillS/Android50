package com.example.will.task37;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText toEditText;
    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toEditText = (EditText) findViewById(R.id.toEditText);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = toEditText.getText().toString();
                if(to.isEmpty()){
                    toEditText.setError("Please enter destination");
                    return;
                }
                boolean check = false;
                for(char c : to.toCharArray()){
                    if(c == '@'){
                        check = true;
                        break;
                    }
                }
                if(!check){
                    toEditText.setError("not have '@' ");
                    return;
                }

                String title = titleEditText.getText().toString();
                if(title.isEmpty()){
                    titleEditText.setError("Please enter title");
                    return;
                }

                String content = contentEditText.getText().toString();

                if(content.isEmpty()){
                    contentEditText.setError("Please enter content");
                    return;
                }

                callMailer();
            }
        });
    }
    private void callMailer(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);

        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:" + toEditText.getText()));
        intent.putExtra(Intent.EXTRA_SUBJECT, titleEditText.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, contentEditText.getText().toString());

        startActivity(Intent.createChooser(intent, null));

    }
}
