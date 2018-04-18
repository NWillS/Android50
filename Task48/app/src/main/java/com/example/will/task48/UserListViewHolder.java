package com.example.will.task48;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess"})
public class UserListViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;
    public UserListViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    public TextView getTextView() {
        return textView;
    }
}
