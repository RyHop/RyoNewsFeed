package com.example.ryan.ryonewsfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NewsTopicPageAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{String.valueOf(R.string.Tab1), String.valueOf(R.string.Tab2), String.valueOf(R.string.Tab3), String.valueOf(R.string.Tab4)};

    public NewsTopicPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BusinessFragment();
        } else if (position == 1) {
            return new PsychologyFragment();
        } else if (position == 2) {
            return new SingaporeFragment();
        } else {
            return new KanyeWestFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];

    }
}
