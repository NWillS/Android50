package com.example.will.task25;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION           = 1;
    private static final String DATABASE_NAME           = "test_database";

    private static final String TABLE_TODO             = "tr_todo";
    private static final String COLUMN_TODO_ID         = "todo_id";
    private static final String COLUMN_TODO_TITLE      = "todo_title";
    private static final String COLUMN_TODO_CONTENTS   = "todo_contents";
    private static final String COLUMN_CREATED         = "created";
    private static final String COLUMN_MODIFIED        = "modified";
    private static final String COLUMN_LIMIT_DATE      = "limit_date";
    private static final String COLUMN_DELETE_FLG      = "delete_flg";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TODO_TABLE = "create table " + TABLE_TODO + " ( " + COLUMN_TODO_ID + " integer primary key, " +
                COLUMN_TODO_TITLE +  " text, " +
                COLUMN_TODO_CONTENTS + " text, " +
                COLUMN_CREATED + " text, " +
                COLUMN_MODIFIED + " text, " +
                COLUMN_LIMIT_DATE + " text, " +
                COLUMN_DELETE_FLG +  " integer" +" ) ";

        sqLiteDatabase.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
    }
}
