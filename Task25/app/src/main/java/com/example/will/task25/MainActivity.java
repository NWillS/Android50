package com.example.will.task25;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.will.task25.FeedReaderContract.FeedEntry;
import com.example.will.task25.TodoRecyclerViewAdapter.TodoAdapterListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoAdapterListener{
    private TodoRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView rv = (RecyclerView) findViewById(R.id.todoListView);
        adapter = new TodoRecyclerViewAdapter();
        adapter.setListener(this);

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
                intent.putExtra("status","newTodo");
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        List<RowData> todoList = new ArrayList<>();
        //rawQueryメソッドでデータを取得
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {
            String sql = "select * from " + FeedEntry.TABLE_TODO +
                    " where " + FeedEntry.COLUMN_DELETE_FLG + " = 0" +
                    " order by " + FeedEntry.COLUMN_LIMIT_DATE + " desc ";
            Cursor cursor = db.rawQuery(sql, null);
            //TextViewに表示
            while (cursor.moveToNext()) {
                int todoID = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String limit = cursor.getString(5);

                RowData todo = new RowData(todoID, title, content, limit);
                todoList.add(todo);
            }
            cursor.close();
        }
        adapter.setTodoList(todoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectedTodo(RowData todo) {
        Intent intent = new Intent(getApplication(), InputActivity.class);
        intent.putExtra("status","updateTodo");
        intent.putExtra("todo",todo);
        startActivity(intent);
    }
}
