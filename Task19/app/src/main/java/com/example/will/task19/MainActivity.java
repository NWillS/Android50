package com.example.will.task19;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<IconModel> iconList;
    IconListRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerView.addItemDecoration(new DividerItemDecoration(10));

        iconList = new ArrayList<>();

        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_black), "black"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_blue), "blue"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_blue_green), "blue_green"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_brown), "brown"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_gray), "gray"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_green), "green"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_light_green), "light_green"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_pink), "pink"));

        adapter = new IconListRecyclerViewAdapter(iconList,this,R.layout.icon_item);

        //setting adapter to recycler
        recyclerView.setAdapter(adapter);
    }

}
