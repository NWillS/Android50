package com.example.will.task18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<RowData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_black),"aaaaa\nbbbbb"));
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_blue),"aaaaa\nbbbsbb"));
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_blue_green),"aaadaa\nbbbbb"));
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_brown),"aaaaa\nbbbfbb"));
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_gray),"aaaaa\nbbbebb"));
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_green),"aasaaa\nbbbbb"));
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_light_green),"aaaaa\nbbcbbb"));
        list.add(new RowData(getResources().getDrawable(R.drawable.icon_pink),"aawaaa\nbbbbb"));



        RecyclerView rv = (RecyclerView) findViewById(R.id.testRecyclerView);
        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(list);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv.addItemDecoration(new DividerItemDecoration(this));

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
    }
}
