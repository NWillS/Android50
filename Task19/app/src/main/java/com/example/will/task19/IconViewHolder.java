package com.example.will.task19;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings({"CanBeFinal", "UnnecessarilyQualifiedInnerClassAccess", "FieldMayBeFinal", "UnnecessaryThis"})
public class IconViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private String text;
    public IconViewHolder(View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int position = Integer.parseInt(IconViewHolder.this.imageView.getTag().toString());

                Toast.makeText(v.getContext(), IconViewHolder.this.text, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public String getText() {
        return this.text;
    }
}
