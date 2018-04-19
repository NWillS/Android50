package com.example.will.task25;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    private List<RowData> todoList;


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new TodoViewHolder(inflate);
    }

    public void setTodoList(List<RowData> todoList) {
        this.todoList = todoList;
    }

    @Override

    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.getTitle().setText(todoList.get(position).getTitle());
        holder.getLimit().setText(todoList.get(position).getLimit());
    }


    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
