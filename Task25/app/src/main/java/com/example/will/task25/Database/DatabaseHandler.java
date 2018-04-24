package com.example.will.task25.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.will.task25.RowData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatabaseHandler {


    public static long insert(SQLiteDatabase db, String title, String content) {
        String nowDate = getNowDate();
        String limited = getLimitDateFrom(nowDate);

        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_TODO_TITLE, title);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_TODO_CONTENTS, content);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_CREATED, nowDate);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_MODIFIED, nowDate);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_LIMIT_DATE, limited);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_DELETE_FLG, 0);

        long ret = -1L;
        try {
            ret = db.insert(FeedReaderContract.FeedEntry.TABLE_TODO, null, contentValues);
        } catch (Exception e) {
            Log.e("DatabaseError", e.getMessage());
        }

        return ret;
    }

    public static int update(SQLiteDatabase db, int todoID, String title, String content) {
        String nowDate = getNowDate();
        String limited = getLimitDateFrom(nowDate);

        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_TODO_TITLE, title);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_TODO_CONTENTS, content);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_MODIFIED, nowDate);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_LIMIT_DATE, limited);


        int ret = -1;
        try {
            String whereClause = FeedReaderContract.FeedEntry.COLUMN_TODO_ID + " = ?";
            String[] whereArgs = {String.valueOf(todoID)};

            ret = db.update(FeedReaderContract.FeedEntry.TABLE_TODO, contentValues, whereClause, whereArgs);
        } catch (Exception e) {
            Log.e("DatabaseError", e.getMessage());
        }

        return ret;
    }

    public static List<RowData> select(SQLiteDatabase db) {
        List<RowData> todoList = new ArrayList<>();

        String sql = "select * from " + FeedReaderContract.FeedEntry.TABLE_TODO +
                " where " + FeedReaderContract.FeedEntry.COLUMN_DELETE_FLG + " = 0" +
                " order by " + FeedReaderContract.FeedEntry.COLUMN_LIMIT_DATE + " desc ";
        Cursor cursor = db.rawQuery(sql, null);
        try {
            while (cursor.moveToNext()) {
                int todoID = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String limit = cursor.getString(5);

                RowData todo = new RowData(todoID, title, content, limit);
                todoList.add(todo);
            }

        } finally {
            cursor.close();
        }

        return todoList;
    }

    public static int delete(SQLiteDatabase db, int todoID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_DELETE_FLG, 1);

        int ret = -1;
        try {
            String whereClause = FeedReaderContract.FeedEntry.COLUMN_TODO_ID + " = ?";
            String[] whereArgs = {String.valueOf(todoID)};
            ret = db.update(FeedReaderContract.FeedEntry.TABLE_TODO, contentValues, whereClause, whereArgs);
        } catch (Exception e) {
            Log.e("DatabaseError", e.getMessage());
        }

        return ret;
    }

    private static String getNowDate() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    private static String getLimitDateFrom(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date date = new Date(dateStr);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        Date limitDate = new Date(calendar.getTimeInMillis());
        return df.format(limitDate);
    }
}
