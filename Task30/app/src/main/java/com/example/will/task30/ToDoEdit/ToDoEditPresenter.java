package com.example.will.task30.ToDoEdit;

import android.database.sqlite.SQLiteDatabase;

import com.example.will.task30.Database.DatabaseHandler;
import com.example.will.task30.Database.DatabaseHelper;
import com.example.will.task30.Database.ToDoData;

import java.util.List;

public class ToDoEditPresenter implements DatabaseViewInterface{
    DatabaseHelper dbHelper;
    ToDoEditView toDoEditView;
    SQLiteDatabase db;


    public ToDoEditPresenter(DatabaseHelper dbHelper, ToDoEditView toDoEditView) {
        this.dbHelper = dbHelper;
        this.toDoEditView = toDoEditView;
        this.db = dbHelper.getWritableDatabase();
    }

    public void onClickButton(ToDoData todo) {
        String title = toDoEditView.getTitleString();
        if(title.isEmpty()){
            toDoEditView.showTitleError("Please enter title");
            return;
        }

        String content = toDoEditView.getContentString();

        if(content.isEmpty()){
            toDoEditView.showContentError("Please enter content");
            return;
        }

        if(todo!=null){
            DatabaseHandler.update(db,todo.getTodoID(),title,content,this);
        }else {
            DatabaseHandler.insert(db,title,content,this);
        }

    }

    @Override
    public void insertDatabaseSuccessfully() {
        toDoEditView.insertSuccessfully();
    }

    @Override
    public void insertDatabaseFailed() {
        toDoEditView.insertFailed();
    }

    @Override
    public void selectedDatabaseSuccessfully(List<ToDoData> toDoDataList) {

    }
}
