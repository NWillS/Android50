package com.example.will.task19;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class IconViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    String text;
    public IconViewHolder(final View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int position = Integer.parseInt(imageView.getTag().toString());

                Toast.makeText(v.getContext(), text , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setText(String text) {
        this.text = text;
    }
}
