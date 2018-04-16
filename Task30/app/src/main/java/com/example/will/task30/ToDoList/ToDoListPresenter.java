package com.example.will.task30.ToDoList;

import com.example.will.task30.Database.ToDoData;

import java.util.List;

public interface ToDoListPresenter {

    void onResume();

    void selectAll();

    void deleteRow(int todoID);
}
