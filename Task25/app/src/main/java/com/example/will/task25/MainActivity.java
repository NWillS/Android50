package com.example.will.task25;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.will.task25.FeedReaderContract.FeedEntry;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<RowData> todoList;
    private TodoRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView rv = (RecyclerView) findViewById(R.id.todoListView);
        adapter = new TodoRecyclerViewAdapter();

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv.addItemDecoration(new DividerItemDecoration(this));

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InputActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoList = new ArrayList<>();

        String sql = "select * from " + FeedReaderContract.FeedEntry.TABLE_TODO;

        //rawQueryメソッドでデータを取得
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            String sql = "select * from " + FeedEntry.TABLE_TODO +
                    " where " + FeedEntry.COLUMN_DELETE_FLG + " = 0" +
                    " order by " + FeedEntry.COLUMN_LIMIT_DATE + " asc ";
            Cursor cursor = db.rawQuery(sql, null);
            //TextViewに表示
            StringBuilder text = new StringBuilder();
            while (cursor.moveToNext()) {
                int todoID = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String limit = cursor.getString(5);

                RowData todo = new RowData(todoID,title,content,limit);
                todoList.add(todo);
            }
            Log.i("System.out",text.toString());
        } finally {
            db.close();
        }
        adapter.setTodoList(todoList);
        adapter.notifyDataSetChanged();
    }
}
