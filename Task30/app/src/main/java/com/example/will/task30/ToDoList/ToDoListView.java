package com.example.will.task30.ToDoList;

import com.example.will.task30.Database.ToDoData;

public interface ToDoListView {
    void reloadList();

    void navigateToEditView(ToDoData todo);

    void showDeleteDialog(int position);
}
