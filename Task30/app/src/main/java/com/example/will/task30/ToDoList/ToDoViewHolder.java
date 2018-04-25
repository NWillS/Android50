package com.example.will.task30.ToDoList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.will.task30.R;

public class ToDoViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView limit;
    private LinearLayout linear;

    public ToDoViewHolder(View itemView) {
        super(itemView);
        linear = (LinearLayout)itemView.findViewById(R.id.linear);
        title = (TextView)itemView.findViewById(R.id.rowTitleTextView);
        limit = (TextView)itemView.findViewById(R.id.rowLimitTextView);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getLimit() {
        return limit;
    }

    public LinearLayout getLinear() {
        return linear;
    }
}
