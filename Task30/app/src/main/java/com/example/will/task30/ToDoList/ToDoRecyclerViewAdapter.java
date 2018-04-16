package com.example.will.task30.ToDoList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.will.task30.Database.ToDoData;
import com.example.will.task30.R;

import java.util.List;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoViewHolder> {
    private List<ToDoData> todoList;

    interface TodoAdapterListener{
        void selectedTodo(ToDoData todo);
        void onLongClicked(int position);
    }

    private TodoAdapterListener listener;

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new ToDoViewHolder(inflate);
    }

    public void setTodoList(List<ToDoData> todoList) {
        this.todoList = todoList;
    }

    @Override
    public void onBindViewHolder(@NonNull final ToDoViewHolder holder, int position) {
        holder.getTitle().setText(todoList.get(position).getTitle());
        holder.getLimit().setText(todoList.get(position).getLimit());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                ToDoData todo = todoList.get(position);
                if(listener != null){
                    listener.selectedTodo(todo);
                } else {
                    listenerError();
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                if(listener != null){
                    listener.onLongClicked(position);
                    return true;
                }
                listenerError();
                return false;
            }
        });
    }

    void setListener(TodoAdapterListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    void listenerError(){
        Log.e("ListenerError","リスナーがセットされていません。");
    }
}
