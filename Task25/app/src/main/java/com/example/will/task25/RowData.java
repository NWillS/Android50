package com.example.will.task25;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"UnqualifiedFieldAccess", "SerializableDeserializableClassInSecureContext", "SerializableHasSerializationMethods", "serial", "DuplicateStringLiteralInspection", "SimpleDateFormatWithoutLocale"})
class RowData implements Serializable{
    private int todoID;
    private String title;
    private String content;
    private String limit;

    RowData(int todoID, String title, String content, String limit) {
        this.todoID = todoID;
        this.title = title;
        this.content = content;
        this.limit = dateFormat(limit);


    }

    public int getTodoID() {
        return todoID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLimit() {
        return limit;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLimit(String limit) {
        this.limit = dateFormat(limit);
    }

    private String dateFormat(String dateStr){
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateStr);
        return df.format(date);
    }
}
