package com.example.will.task43;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "CanBeFinal"})
public class MusicListViewHolder extends RecyclerView.ViewHolder {
    private final ImageView playImageView;
    private final ImageView stopImageView;
    private final TextView musicNameTextView;

    public MusicListViewHolder(View itemView) {
        super(itemView);
        playImageView = (ImageView)itemView.findViewById(R.id.playImageView);
        stopImageView = (ImageView)itemView.findViewById(R.id.stopImageView);
        musicNameTextView = (TextView)itemView.findViewById(R.id.musicNameTextView);
    }

    public ImageView getPlayImageView() {
        return playImageView;
    }

    public ImageView getStopImageView() {
        return stopImageView;
    }

    public TextView getMusicNameTextView() {
        return musicNameTextView;
    }
}
