package com.example.will.myapplication;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"UnnecessarilyQualifiedInnerClassAccess", "ConstantConditions"})
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Drawable> iconList = new ArrayList<>();

        iconList.add(getResources().getDrawable(R.drawable.apple));
        iconList.add(getResources().getDrawable(R.drawable.android));

        TabLayoutFragmentPagerAdapter adapter = new TabLayoutFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(iconList.size());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0; i <  iconList.size();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(iconList.get(i));
        }
    }
}
