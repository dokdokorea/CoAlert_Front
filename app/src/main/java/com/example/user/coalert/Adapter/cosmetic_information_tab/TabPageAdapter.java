package com.example.user.coalert.Adapter.cosmetic_information_tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabPageAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                ingredient tabFragment1 = new ingredient();
                return tabFragment1;
            case 1:
                SimpleReview tabFragment2 = new SimpleReview();
                return tabFragment2;
            case 2:
                DetailReview tabFragment3 = new DetailReview();
                return tabFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}