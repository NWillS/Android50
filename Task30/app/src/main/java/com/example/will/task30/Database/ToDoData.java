package com.example.will.task30.Database;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoData  implements Serializable {
    private int todoID;
    private String title;
    private String content;
    private String limit;

    public ToDoData(int todoID, String title, String content, String limit) {
        this.todoID = todoID;
        this.title = title;
        this.content = content;
        this.limit = limit;
    }

    public int getTodoID() {
        return this.todoID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getLimit() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(limit);
        return df.format(date);
    }
}
