package com.example.will.task43;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class MusicListRecycleViewAdapter extends RecyclerView.Adapter<MusicListViewHolder> {
    private List<RowData> musicList;
    private int playing = -1;

    interface MusicListListener{
        void play(int filePath);
        void stop();
    }

    private MusicListListener listener;

    void setListener(MusicListListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MusicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new MusicListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicListViewHolder holder, final int position) {
        holder.getMusicNameTextView().setText(musicList.get(position).getMusicName());
        if (position == playing){
            holder.play();
            holder.setPlaying(true);
        }else{
            holder.stop();
            holder.setPlaying(false);
        }
        holder.getPlayImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.isPlaying()) {
                    listener.stop();
                    playing = -1;
                } else {
                    listener.play(musicList.get(position).getId());
                    playing = position;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
    public void setMusicList(List<RowData> musicList) {
        this.musicList = musicList;
    }

    public void complete(){
        playing = -1;
        listener.stop();
    }
}
