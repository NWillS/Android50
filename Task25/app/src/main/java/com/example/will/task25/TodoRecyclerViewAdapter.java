package com.example.will.task25;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "AssignmentOrReturnOfFieldWithMutableType", "UnqualifiedInnerClassAccess", "AnonymousClassVariableHidesContainingMethodVariable"})
class TodoRecyclerViewAdapter extends Adapter<TodoViewHolder> {
    private List<RowData> todoList;

    @SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
    interface TodoAdapterListener{
        void selectedTodo(RowData todo);
        void onLongClicked(int position);
    }

    private TodoAdapterListener listener;

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
    public void onBindViewHolder(@NonNull final TodoViewHolder holder, int position) {
        holder.getTitle().setText(todoList.get(position).getTitle());
        holder.getLimit().setText(todoList.get(position).getLimit());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                RowData todo = todoList.get(position);
                if(listener != null){
                    listener.selectedTodo(todo);
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

}
