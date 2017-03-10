package com.example.chris.mystats_univeristy;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by c077ing on 08/03/2017.
 */

public class Adapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"Course Overview","Study Info Stats","Entry Info","Employment Stats",
            "Cost Stats","Satisfaction Stats","User Ratings/Comments"};
    Context context;
    private int pageCount = 6;

    public Adapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentSelector frag = new FragmentSelector(position);
        return frag;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
    }
}
