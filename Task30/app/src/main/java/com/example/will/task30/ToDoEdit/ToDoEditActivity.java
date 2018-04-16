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

import java.util.List;

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

        setupResources();

        checkStatus();

        registerButton.setOnClickListener(this);

        presenter = new ToDoEditPresenter(new DatabaseHelper(this),this);
    }

    private void setupResources(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);
        registerButton = findViewById(R.id.registerButton);
    }

    private void checkStatus(){
        status = getIntent().getStringExtra("status");


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
        if (todo!=null){
            presenter.onClickButton(todo);
        } else {
            presenter.onClickButton(null);
        }
    }


    @Override
    public String getTitleString() {
        return titleEditText.getText().toString();
    }

    @Override
    public String getContentString() {
        return contentEditText.getText().toString();
    }

    @Override
    public void showTitleError(String titleError) {
        titleEditText.setError(titleError);
    }

    @Override
    public void showContentError(String contentError) {
        contentEditText.setError(contentError);
    }

    @Override
    public void insertSuccessfully() {
        finish();
    }

    @Override
    public void insertFailed() {
        Toast.makeText(this, "Database Inserted Failed", Toast.LENGTH_SHORT).show();
    }
}
