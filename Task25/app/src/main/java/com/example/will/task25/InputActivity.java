package com.example.will.task25;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
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



@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "UnqualifiedInnerClassAccess", "DuplicateStringLiteralInspection", "SimpleDateFormatWithoutLocale"})
public class InputActivity extends AppCompatActivity {
    private RowData todo;
    private String status;
    private EditText titleEditText;
    private EditText contentEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Intent intent = getIntent();
        status = intent.getStringExtra("status");

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        if(status.equals("updateTodo")){
            todo = (RowData) intent.getSerializableExtra("todo");
            toolbar.setTitle(todo.getTitle());
            titleEditText.setText(todo.getTitle());
            contentEditText.setText(todo.getContent());
            registerButton.setText("更新");
        } else {
            toolbar.setTitle("新規ToDo");
        }
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

                String nowDate = getNowDate();
                String limited = getLimitDateFrom(nowDate);

                if(status.equals("updateTodo")){
                    update(db,nowDate,limited);
                } else {
                    insert(db,nowDate,limited);
                }
                finish();
            }
        });
    }

    private void insert(SQLiteDatabase db, String nowDate,String limited){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COLUMN_TODO_TITLE, titleEditText.getText().toString());
        contentValues.put(FeedEntry.COLUMN_TODO_CONTENTS, contentEditText.getText().toString());
        contentValues.put(FeedEntry.COLUMN_CREATED, nowDate);
        contentValues.put(FeedEntry.COLUMN_MODIFIED, nowDate);
        contentValues.put(FeedEntry.COLUMN_LIMIT_DATE, limited);
        contentValues.put(FeedEntry.COLUMN_DELETE_FLG, 0);

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
    }

    private void update(SQLiteDatabase db, String nowDate,String limited){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COLUMN_TODO_TITLE, titleEditText.getText().toString());
        contentValues.put(FeedEntry.COLUMN_TODO_CONTENTS, contentEditText.getText().toString());
        contentValues.put(FeedEntry.COLUMN_MODIFIED, nowDate);
        contentValues.put(FeedEntry.COLUMN_LIMIT_DATE, limited);


        int ret;
        try {
            String whereClause = FeedEntry.COLUMN_TODO_ID + " = ?";
            String[] whereArgs = {String.valueOf(todo.getTodoID())};

            ret = db.update(FeedEntry.TABLE_TODO,contentValues,whereClause,whereArgs);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(getApplication(), "Update失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplication(), "Update成功", Toast.LENGTH_SHORT).show();
        }
    }


    private String getNowDate(){
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    private String getLimitDateFrom(String dateStr){
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date date = new Date(dateStr);
        calendar.setTime(date);
        calendar.add(Calendar.DATE,7);
        Date limitDate = new Date(calendar.getTimeInMillis());
        return df.format(limitDate);
    }
}
