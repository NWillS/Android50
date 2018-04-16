package com.example.will.task30.ToDoList;

import com.example.will.task30.Database.ToDoData;

import java.util.List;

public interface ToDoListView {
    void reloadList();

    void navigateToEditView(ToDoData todo);

    void showDeleteDialog(int position);

    void printToDoList(List<ToDoData> todoList);

    void insertSuccessfully();

    void insertFailed();
}
