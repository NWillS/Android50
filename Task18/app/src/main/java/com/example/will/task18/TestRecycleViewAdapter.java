package com.example.will.task18;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class TestRecycleViewAdapter extends RecyclerView.Adapter<TestViewHolder> {
    private List<RowData> list;

    public TestRecycleViewAdapter(List<RowData> list) {
        this.list = list;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        TestViewHolder vh = new TestViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        holder.imageView.setImageDrawable(list.get(position).getImage());
        holder.detailView.setText(list.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
