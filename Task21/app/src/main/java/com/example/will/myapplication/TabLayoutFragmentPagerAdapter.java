package com.example.will.myapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

@SuppressWarnings({"CanBeFinal", "FieldMayBeFinal", "UnqualifiedFieldAccess"})
class TabLayoutFragmentPagerAdapter extends FragmentPagerAdapter {
    private CharSequence[] tabTitles = {"Apple", "Android"};

    TabLayoutFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Nullable
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstTabFragment();
            case 1:
                return new SecondTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
