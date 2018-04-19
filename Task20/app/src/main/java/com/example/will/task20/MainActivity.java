package com.example.will.task20;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
public class MainActivity extends AppCompatActivity {

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Page> pageList = new ArrayList<>();
        pageList.add(new Page("aaa"));
        pageList.add(new Page("bbb"));
        pageList.add(new Page("ccc"));

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        FragmentPagerAdapter adapter = new TestViewPager(getSupportFragmentManager(),pageList);
        pager.setAdapter(adapter);

    }
}
