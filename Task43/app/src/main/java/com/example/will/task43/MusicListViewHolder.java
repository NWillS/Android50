package com.example.will.task43;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicListViewHolder extends RecyclerView.ViewHolder {
    private final ImageView playImageView;
    private final TextView musicNameTextView;
    private boolean playing;

    public MusicListViewHolder(View itemView) {
        super(itemView);
        playImageView = (ImageView)itemView.findViewById(R.id.playImageView);
        musicNameTextView = (TextView)itemView.findViewById(R.id.musicNameTextView);
        playing = false;
    }

    public ImageView getPlayImageView() {
        return playImageView;
    }


    public TextView getMusicNameTextView() {
        return musicNameTextView;
    }


    public boolean isPlaying() {
        return this.playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void play(){
        playImageView.setImageResource(R.drawable.stop);
    }

    public void stop(){
        playImageView.setImageResource(R.drawable.play);
    }
}
