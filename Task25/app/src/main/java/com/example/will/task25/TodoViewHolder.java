package com.example.will.task25;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TodoViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView limit;
    public TodoViewHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.rowTitleTextView);
        limit = (TextView)itemView.findViewById(R.id.rowLimitTextView);
    }

    public TextView getTitle() {
        return this.title;
    }

    public TextView getLimit() {
        return this.limit;
    }
}
