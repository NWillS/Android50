package com.example.will.task43;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "AssignmentOrReturnOfFieldWithMutableType"})
class MusicListRecycleViewAdapter extends RecyclerView.Adapter<MusicListViewHolder> {
    private List<RowData> musicList;

    @SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
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

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MusicListViewHolder holder, final int position) {
        holder.getMusicNameTextView().setText(musicList.get(position).getMusicName());

        holder.getPlayImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.play(musicList.get(position).getId());

            }
        });
        holder.getStopImageView().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                listener.stop();
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
}
