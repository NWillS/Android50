package com.example.will.task19;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class IconListRecyclerViewAdapter extends RecyclerView.Adapter<IconViewHolder> {
    private ArrayList<IconModel> itemsList;
    private Activity mContext;
    int layout;

    public IconListRecyclerViewAdapter(ArrayList<IconModel> itemsList, Activity mContext, int layout) {
        this.itemsList = itemsList;
        this.mContext = mContext;
        this.layout = layout;
    }



    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_item, parent,false);
        IconViewHolder vh = new IconViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        IconModel item = itemsList.get(position);


        holder.imageView.setTag(position);
        holder.imageView.setImageDrawable(item.getDrawable());
        holder.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return (itemsList != null ? itemsList.size() : 0);
    }
}
