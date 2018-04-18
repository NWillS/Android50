package com.example.will.task48;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnnecessarilyQualifiedInnerClassAccess", "AssignmentOrReturnOfFieldWithMutableType"})
class UserListRecycleViewAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    private List<User> userList = new ArrayList<>();
    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new UserListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        String name = userList.get(position).getName();
        String age = userList.get(position).getAge();
        holder.getTextView().setText(String.format("%sさん、%s歳。",name,age));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
