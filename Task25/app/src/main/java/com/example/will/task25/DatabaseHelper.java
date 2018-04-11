package com.example.will.task25;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION           = 1;
    private static final String DATABASE_NAME           = "test_database";


    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TODO_TABLE = "create table " + FeedReaderContract.FeedEntry.TABLE_TODO + " ( " +
                FeedReaderContract.FeedEntry.COLUMN_TODO_ID + " integer primary key autoincrement, " +
                FeedReaderContract.FeedEntry.COLUMN_TODO_TITLE +  " text, " +
                FeedReaderContract.FeedEntry.COLUMN_TODO_CONTENTS + " text, " +
                FeedReaderContract.FeedEntry.COLUMN_CREATED + " text, " +
                FeedReaderContract.FeedEntry.COLUMN_MODIFIED + " text, " +
                FeedReaderContract.FeedEntry.COLUMN_LIMIT_DATE + " text, " +
                FeedReaderContract.FeedEntry.COLUMN_DELETE_FLG +  " integer" + " ) ";

        sqLiteDatabase.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
