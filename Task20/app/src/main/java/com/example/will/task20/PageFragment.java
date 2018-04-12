package com.example.will.task20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PageFragment extends Fragment {
    TextView textView;
    TextView pageView;
    private List<Page> pageList;
    private int position;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        pageView = (TextView) view.findViewById(R.id.currentPage);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textView.setText(pageList.get(position).getText());
        pageView.setText(position+1 + " / " + pageList.size());

    }

    public static PageFragment newInstance(List<Page> pageList, int position) {

        Bundle args = new Bundle();

        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);

        fragment.setPageList(pageList);
        fragment.setPosition(position);
        return fragment;
    }

    public List<Page> getPageList() {
        return this.pageList;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}