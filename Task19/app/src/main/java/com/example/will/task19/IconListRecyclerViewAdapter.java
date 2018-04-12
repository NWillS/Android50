package com.example.will.task19;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

@SuppressWarnings({"CanBeFinal", "FieldMayBeFinal", "UnnecessarilyQualifiedInnerClassAccess", "UnnecessaryThis", "ImplicitCallToSuper", "AssignmentOrReturnOfFieldWithMutableType"})
class IconListRecyclerViewAdapter extends RecyclerView.Adapter<IconViewHolder> {
    private ArrayList<IconModel> itemsList;

    IconListRecyclerViewAdapter(ArrayList<IconModel> itemsList) {
        this.itemsList = itemsList;
    }



    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_item, parent,false);
        return new IconViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        IconModel item = this.itemsList.get(position);


        holder.getImageView().setTag(position);
        holder.getImageView().setImageDrawable(item.getDrawable());
        holder.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return (this.itemsList != null ? this.itemsList.size() : 0);
    }
}
