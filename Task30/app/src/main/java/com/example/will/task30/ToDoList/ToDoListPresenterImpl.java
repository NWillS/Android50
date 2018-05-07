package com.example.will.task30.ToDoList;

import android.database.sqlite.SQLiteDatabase;

import com.example.will.task30.Database.DatabaseHandler;
import com.example.will.task30.Database.DatabaseHelper;
import com.example.will.task30.Database.ToDoData;
import com.example.will.task30.ToDoEdit.DatabaseViewInterface;

import java.util.List;

public class ToDoListPresenterImpl implements ToDoListPresenter,
        ToDoRecyclerViewAdapter.TodoAdapterListener,
        DatabaseViewInterface{

    private ToDoListView toDoListView;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public ToDoListPresenterImpl(DatabaseHelper dbHelper, ToDoListView toDoListView) {
        this.toDoListView = toDoListView;
        this.dbHelper = dbHelper;
        this.db = dbHelper.getWritableDatabase();
    }

    @Override
    public void onResume() {
        if (toDoListView != null){
            toDoListView.reloadList();
        }
    }

    @Override
    public void selectAll() {
        DatabaseHandler.select(db,this);
    }

    @Override
    public void deleteRow(int todoID) {
        DatabaseHandler.delete(db,todoID,this);
    }


    @Override
    public void selectedTodo(ToDoData todo) {
        if (toDoListView != null){
            toDoListView.navigateToEditView(todo);
        }
    }

    @Override
    public void onLongClicked(int position) {
        if (toDoListView != null){
            toDoListView.showDeleteDialog(position);
        }
    }

    @Override
    public void insertDatabaseSuccessfully() {
        toDoListView.insertSuccessfully();
    }

    @Override
    public void insertDatabaseFailed() {

    }

    @Override
    public void selectedDatabaseSuccessfully(List<ToDoData> toDoDataList) {
        toDoListView.printToDoList(toDoDataList);
    }
}
