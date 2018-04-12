package com.example.will.task25;

import android.content.ContentValues;
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
import android.widget.Toast;

import com.example.will.task25.FeedReaderContract.FeedEntry;
import com.example.will.task25.TodoRecyclerViewAdapter.TodoAdapterListener;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "UnqualifiedInnerClassAccess", "TryFinallyCanBeTryWithResources", "DuplicateStringLiteralInspection"})
public class MainActivity extends AppCompatActivity implements TodoAdapterListener,DeleteDialogFragment.DeleteDialogListener{
    private TodoRecyclerViewAdapter adapter;
    private int position;
    private List<RowData> todoList;

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

    @Override
    protected void onResume() {
        super.onResume();
        reloadList();
    }


    private void reloadList(){
        todoList = new ArrayList<>();
        //rawQueryメソッドでデータを取得
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            String sql = "select * from " + FeedEntry.TABLE_TODO +
                    " where " + FeedEntry.COLUMN_DELETE_FLG + " = 0" +
                    " order by " + FeedEntry.COLUMN_LIMIT_DATE + " asc ";
            Cursor cursor = db.rawQuery(sql, null);
            //TextViewに表示
            while (cursor.moveToNext()) {
                int todoID = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String limit = cursor.getString(5);

                RowData todo = new RowData(todoID,title,content,limit);
                todoList.add(todo);
            }
            cursor.close();
        } finally {

            db.close();
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

    @Override
    public void onLongClicked(int position) {
        this.position = position;
        DeleteDialogFragment dialogFragment = new DeleteDialogFragment();
        dialogFragment.setListener(this);
        dialogFragment.show(getFragmentManager(),"delete");
    }

    @Override
    public void onClickOk() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COLUMN_DELETE_FLG, 1);

        String whereArgs[] = {String.valueOf(todoList.get(position).getTodoID())};

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            String whereClause = FeedEntry.COLUMN_TODO_ID + " = ?";
            ret = db.update(FeedEntry.TABLE_TODO,contentValues, whereClause, whereArgs);
        } finally {
            db.close();
        }

        reloadList();

        if (ret == -1){
            Toast.makeText(this, "Delete失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete成功", Toast.LENGTH_SHORT).show();
        }
    }
}
