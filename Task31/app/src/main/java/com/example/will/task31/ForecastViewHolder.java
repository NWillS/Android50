package com.example.will.task31;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ForecastViewHolder extends RecyclerView.ViewHolder {
    private ImageView iconImageView;
    private TextView dateTextView;
    private TextView forecastTextView;

    public ForecastViewHolder(View itemView) {
        super(itemView);

        iconImageView = (ImageView) itemView.findViewById(R.id.imageView);
        dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        forecastTextView = (TextView) itemView.findViewById(R.id.forecastTextView);
    }

    public ImageView getIconImageView() {
        return this.iconImageView;
    }

    public TextView getDateTextView() {
        return this.dateTextView;
    }

    public TextView getForecastTextView() {
        return this.forecastTextView;
    }

    public void setIconImageView(ImageView iconImageView) {
        this.iconImageView = iconImageView;
    }

    public void setDateTextView(TextView dateTextView) {
        this.dateTextView = dateTextView;
    }

    public void setForecastTextView(TextView forecastTextView) {
        this.forecastTextView = forecastTextView;
    }
}
