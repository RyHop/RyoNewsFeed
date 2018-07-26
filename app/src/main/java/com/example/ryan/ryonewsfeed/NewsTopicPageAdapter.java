package com.example.ryan.ryonewsfeed;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class NewsTopicPageAdapter extends FragmentPagerAdapter {
    Context context;





    public NewsTopicPageAdapter(FragmentManager fm, Context R2) {
        super(fm);
        this.context = R2;
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

        switch (position) {
            case 0:
                return context.getString(R.string.Tab1);
            case 1:
                return context.getString(R.string.Tab2);
            case 2:
                return context.getString(R.string.Tab3);
            case 3:
                return context.getString(R.string.Tab4);
            default:
                return context.getString(R.string.Tab1);


        }
    }
}
