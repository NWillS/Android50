package com.example.will.task30.ToDoList;

import com.example.will.task30.Database.ToDoData;

public class ToDoListPresenterImpl implements ToDoListPresenter,
        ToDoRecyclerViewAdapter.TodoAdapterListener {

    private ToDoListView toDoListView;

    public ToDoListPresenterImpl(ToDoListView toDoListView) {
        this.toDoListView = toDoListView;
    }

    @Override
    public void onResume() {
        if (toDoListView != null){
            toDoListView.reloadList();
        }
    }


    @Override
    public void selectedTodo(ToDoData todo) {
        if (toDoListView != null){
            toDoListView.navigateToEditView(todo);
        }
    }

    @Override
    public void onLongClicked(int position) {
        if (toDoListView != null){
            toDoListView.showDeleteDialog(position);
        }
    }

}
