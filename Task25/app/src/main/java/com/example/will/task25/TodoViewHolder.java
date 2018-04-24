package com.example.will.task25;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TodoViewHolder extends ViewHolder {
    private TextView title;
    private TextView limit;
    private LinearLayout linear;

    public TodoViewHolder(View itemView) {
        super(itemView);
        linear = (LinearLayout) itemView.findViewById(R.id.linear);
        title = (TextView) itemView.findViewById(R.id.rowTitleTextView);
        limit = (TextView) itemView.findViewById(R.id.rowLimitTextView);
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
