package com.example.will.task20;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

@SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        FragmentPagerAdapter adapter = new TestViewPager(getSupportFragmentManager());
        pager.setAdapter(adapter);

    }
}
