package com.example.will.task18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add("1\n2\n3\n4\n5\n6\n7");
        list.add("1\n2\n3\n4\n5\n6\n7\n8");
        list.add("1\n2\n3\n4\n5\n6\n7\n8\n9");
        list.add("1\n2\n3\n4\n5\n6\n7\n8\n9\n10");
        list.add("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11");
        list.add("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12");

        RecyclerView rv = (RecyclerView) findViewById(R.id.testRecyclerView);
        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(this.createDataSet());

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv.addItemDecoration(new DividerItemDecoration(this));

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
    }

    private List<RowData> createDataSet() {
        List<RowData> dataSet = new ArrayList<>();
        for (String str : list) {
            RowData data = new RowData();
            data.setTitle("");
            data.setDetail( str );

            dataSet.add(data);
        }
        return dataSet;
    }
}
