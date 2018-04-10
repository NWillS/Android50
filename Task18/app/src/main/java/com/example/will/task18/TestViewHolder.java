package com.example.will.task18;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TestViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView detailView;
    public TestViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        detailView = (TextView) itemView.findViewById(R.id.detail);

    }
}
