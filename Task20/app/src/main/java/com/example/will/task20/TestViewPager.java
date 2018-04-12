package com.example.will.task20;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

class TestViewPager extends FragmentPagerAdapter {
    List<Page> pageList;

    TestViewPager(FragmentManager fm, List<Page> pageList) {
        super(fm);
        this.pageList = pageList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment = PageFragment.newInstance(pageList,position);
        return fragment;
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
