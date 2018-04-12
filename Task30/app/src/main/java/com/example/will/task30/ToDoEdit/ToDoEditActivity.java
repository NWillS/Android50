package com.example.will.task30.ToDoEdit;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.will.task30.Database.DatabaseHandler;
import com.example.will.task30.Database.DatabaseHelper;
import com.example.will.task30.Database.ToDoData;
import com.example.will.task30.R;

public class ToDoEditActivity extends Activity implements ToDoEditView, View.OnClickListener{
    Toolbar toolbar;
    Button registerButton;
    EditText titleEditText;
    EditText contentEditText;
    ToDoEditPresenter presenter;



    ToDoData todo;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_edit);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);


        status = getIntent().getStringExtra("status");

        presenter = new ToDoEditPresenterImlpl(this);

        if(status.equals("updateTodo")){
            todo = (ToDoData) getIntent().getSerializableExtra("todo");
            toolbar.setTitle(todo.getTitle());
            titleEditText.setText(todo.getTitle());
            contentEditText.setText(todo.getContent());
            registerButton.setText("更新");
        } else {
            toolbar.setTitle("新規ToDo");
        }
    }

    @Override
    public void onClick(View view) {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();



        int ret;
        DatabaseHelper dbHelper= new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (status.equals("updateTodo")){
            if (DatabaseHandler.update(db,todo.getTodoID(),title,content) != -1){
                Toast.makeText(getApplication(), "更新完了", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (DatabaseHandler.insert(db,title,content) != -1L){
                Toast.makeText(getApplication(), "登録完了", Toast.LENGTH_SHORT).show();
            }

        }

        finish();
    }
}
