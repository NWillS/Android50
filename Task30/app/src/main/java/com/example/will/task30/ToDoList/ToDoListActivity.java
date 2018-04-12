package com.example.will.task30.ToDoList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.will.task30.Database.DatabaseHandler;
import com.example.will.task30.Database.DatabaseHelper;
import com.example.will.task30.Database.ToDoData;
import com.example.will.task30.R;
import com.example.will.task30.ToDoEdit.ToDoEditActivity;

import java.util.List;

public class ToDoListActivity extends Activity implements ToDoListView,View.OnClickListener,
        DeleteDialogFragment.DeleteDialogListener{
    private ToDoRecyclerViewAdapter adapter;
    private ToDoListPresenter presenter;
    private List<ToDoData> todoList;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        RecyclerView rv = (RecyclerView) findViewById(R.id.todoListView);
        adapter = new ToDoRecyclerViewAdapter();
        Button button = (Button) findViewById(R.id.addButton);
        presenter = new ToDoListPresenterImpl(this);
        findViewById(R.id.addButton).setOnClickListener(this);

        adapter.setListener((ToDoRecyclerViewAdapter.TodoAdapterListener) presenter);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.addItemDecoration(new DividerItemDecoration(this));
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ToDoEditActivity.class);
        intent.putExtra("status","newTodo");
        startActivity(intent);
    }

    @Override
    public void reloadList() {

        DatabaseHelper dbHelper= new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        todoList = DatabaseHandler.select(db);
        adapter.setTodoList(todoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToEditView(ToDoData todo) {
        Intent intent = new Intent(getApplication(), ToDoEditActivity.class);
        intent.putExtra("status","updateTodo");
        intent.putExtra("todo",todo);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int position) {
        this.position = position;
        DeleteDialogFragment dialogFragment = new DeleteDialogFragment();
        dialogFragment.setListener(this);
        dialogFragment.show(getFragmentManager(),"delete");
    }

    @Override
    public void onClickOk() {

        DatabaseHelper dbHelper= new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(DatabaseHandler.delete(db,todoList.get(position).getTodoID()) != -1){
            Toast.makeText(getApplication(), "削除完了", Toast.LENGTH_SHORT).show();
            reloadList();
        }

    }
}
