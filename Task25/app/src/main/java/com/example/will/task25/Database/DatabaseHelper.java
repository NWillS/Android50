package com.example.will.task25.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.will.task25.Database.FeedReaderContract.FeedEntry;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION           = 1;
    private static final String DATABASE_NAME           = "test_database";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TODO_TABLE = "create table " + FeedEntry.TABLE_TODO + " ( " +
                FeedEntry.COLUMN_TODO_ID + " integer primary key autoincrement, " +
                FeedEntry.COLUMN_TODO_TITLE +  " text, " +
                FeedEntry.COLUMN_TODO_CONTENTS + " text, " +
                FeedEntry.COLUMN_CREATED + " text, " +
                FeedEntry.COLUMN_MODIFIED + " text, " +
                FeedEntry.COLUMN_LIMIT_DATE + " text, " +
                FeedEntry.COLUMN_DELETE_FLG +  " integer" + " ) ";

        sqLiteDatabase.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
