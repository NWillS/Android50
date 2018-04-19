package com.example.will.task25;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.will.task25.FeedReaderContract.FeedEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final EditText contentEditText = (EditText) findViewById(R.id.contentEditText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                DatabaseHelper databaseHelper = new DatabaseHelper(getApplication());
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                String created = getNowDate();
                String limited = getLimitDateFrom(created);

                ContentValues contentValues = new ContentValues();
                contentValues.put(FeedEntry.COLUMN_TODO_TITLE,title);
                contentValues.put(FeedEntry.COLUMN_TODO_CONTENTS,content);
                contentValues.put(FeedEntry.COLUMN_CREATED,created);
                contentValues.put(FeedEntry.COLUMN_MODIFIED,created);
                contentValues.put(FeedEntry.COLUMN_LIMIT_DATE,limited);
                contentValues.put(FeedEntry.COLUMN_DELETE_FLG,0);

                long ret;
                try {
                    ret = db.insert(FeedEntry.TABLE_TODO, null, contentValues);
                } finally {
                    db.close();
                }
                if (ret == -1L) {
                    Toast.makeText(getApplication(), "Insert失敗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Insert成功", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }



    private String getNowDate(){
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    private String getLimitDateFrom(String dateStr){
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date date = new Date(dateStr);
        calendar.setTime(date);
        calendar.add(Calendar.DATE,7);
        Date limitDate = new Date(calendar.getTimeInMillis());
        return df.format(limitDate);
    }
}
