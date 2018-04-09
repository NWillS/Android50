package com.example.will.task19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

@SuppressWarnings({"UnnecessarilyQualifiedInnerClassAccess", "UnnecessaryQualifierForThis"})
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerView.addItemDecoration(new DividerItemDecoration(10));

        ArrayList<IconModel> iconList = new ArrayList<>();

        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_black), "black"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_blue), "blue"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_blue_green), "blue_green"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_brown), "brown"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_gray), "gray"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_green), "green"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_light_green), "light_green"));
        iconList.add(new IconModel(getResources().getDrawable(R.drawable.icon_pink), "pink"));

        IconListRecyclerViewAdapter adapter = new IconListRecyclerViewAdapter(iconList);

        //setting adapter to recycler
        recyclerView.setAdapter(adapter);
    }

}
