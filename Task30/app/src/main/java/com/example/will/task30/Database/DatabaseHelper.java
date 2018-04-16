package com.example.will.task30.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.will.task30.Database.DatabaseContract.ToDoEntry;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION           = 1;
    private static final String DATABASE_NAME           = "test_database";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TODO_TABLE = "create table " + ToDoEntry.TABLE_TODO + " ( " +
                ToDoEntry.COLUMN_TODO_ID + " integer primary key autoincrement, " +
                ToDoEntry.COLUMN_TODO_TITLE +  " text, " +
                ToDoEntry.COLUMN_TODO_CONTENTS + " text, " +
                ToDoEntry.COLUMN_CREATED + " text, " +
                ToDoEntry.COLUMN_MODIFIED + " text, " +
                ToDoEntry.COLUMN_LIMIT_DATE + " text, " +
                ToDoEntry.COLUMN_DELETE_FLG +  " integer" + " ) ";

        sqLiteDatabase.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}