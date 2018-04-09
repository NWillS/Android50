package com.example.will.task20;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class TestViewPager extends FragmentPagerAdapter {
    private static final int PAGE_NUM = 3;

    TestViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new FirstPageFragment();
                break;
            case 1:
                fragment = new SecondPageFragment();
                break;
            default:
                fragment = new ThirdPageFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }
}
