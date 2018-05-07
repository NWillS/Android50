package com.example.will.task30.ToDoEdit;

public interface ToDoEditView {

    String getTitleString();

    String getContentString();

    void showTitleError(String titleError);

    void showContentError(String contentError);

    void insertSuccessfully();

    void insertFailed();
}
