package com.example.will.task30.ToDoEdit;

import com.example.will.task30.Database.ToDoData;

import java.util.List;

public interface DatabaseViewInterface {
    void insertDatabaseSuccessfully();

    void insertDatabaseFailed();

    void selectedDatabaseSuccessfully(List<ToDoData> toDoDataList);
}
